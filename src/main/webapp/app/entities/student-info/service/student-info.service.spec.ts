import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { IStudentInfo } from '../student-info.model';
import { sampleWithRequiredData, sampleWithPartialData, sampleWithFullData } from '../student-info.test-samples';

import { StudentInfoService } from './student-info.service';

const requireRestSample: IStudentInfo = {
  ...sampleWithRequiredData,
};

describe('StudentInfo Service', () => {
  let service: StudentInfoService;
  let httpMock: HttpTestingController;
  let expectedResult: IStudentInfo | IStudentInfo[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(StudentInfoService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  describe('Service methods', () => {
    it('should find an element', () => {
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.find(123).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of StudentInfo', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    describe('addStudentInfoToCollectionIfMissing', () => {
      it('should add a StudentInfo to an empty array', () => {
        const studentInfo: IStudentInfo = sampleWithRequiredData;
        expectedResult = service.addStudentInfoToCollectionIfMissing([], studentInfo);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(studentInfo);
      });

      it('should not add a StudentInfo to an array that contains it', () => {
        const studentInfo: IStudentInfo = sampleWithRequiredData;
        const studentInfoCollection: IStudentInfo[] = [
          {
            ...studentInfo,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addStudentInfoToCollectionIfMissing(studentInfoCollection, studentInfo);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a StudentInfo to an array that doesn't contain it", () => {
        const studentInfo: IStudentInfo = sampleWithRequiredData;
        const studentInfoCollection: IStudentInfo[] = [sampleWithPartialData];
        expectedResult = service.addStudentInfoToCollectionIfMissing(studentInfoCollection, studentInfo);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(studentInfo);
      });

      it('should add only unique StudentInfo to an array', () => {
        const studentInfoArray: IStudentInfo[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const studentInfoCollection: IStudentInfo[] = [sampleWithRequiredData];
        expectedResult = service.addStudentInfoToCollectionIfMissing(studentInfoCollection, ...studentInfoArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const studentInfo: IStudentInfo = sampleWithRequiredData;
        const studentInfo2: IStudentInfo = sampleWithPartialData;
        expectedResult = service.addStudentInfoToCollectionIfMissing([], studentInfo, studentInfo2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(studentInfo);
        expect(expectedResult).toContain(studentInfo2);
      });

      it('should accept null and undefined values', () => {
        const studentInfo: IStudentInfo = sampleWithRequiredData;
        expectedResult = service.addStudentInfoToCollectionIfMissing([], null, studentInfo, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(studentInfo);
      });

      it('should return initial array if no StudentInfo is added', () => {
        const studentInfoCollection: IStudentInfo[] = [sampleWithRequiredData];
        expectedResult = service.addStudentInfoToCollectionIfMissing(studentInfoCollection, undefined, null);
        expect(expectedResult).toEqual(studentInfoCollection);
      });
    });

    describe('compareStudentInfo', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareStudentInfo(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 123 };
        const entity2 = null;

        const compareResult1 = service.compareStudentInfo(entity1, entity2);
        const compareResult2 = service.compareStudentInfo(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 456 };

        const compareResult1 = service.compareStudentInfo(entity1, entity2);
        const compareResult2 = service.compareStudentInfo(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 123 };

        const compareResult1 = service.compareStudentInfo(entity1, entity2);
        const compareResult2 = service.compareStudentInfo(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
