import { inject, Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IStudentInfo } from '../student-info.model';

export type EntityResponseType = HttpResponse<IStudentInfo>;
export type EntityArrayResponseType = HttpResponse<IStudentInfo[]>;

@Injectable({ providedIn: 'root' })
export class StudentInfoService {
  protected http = inject(HttpClient);
  protected applicationConfigService = inject(ApplicationConfigService);

  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/student-infos');

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IStudentInfo>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IStudentInfo[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  getStudentInfoIdentifier(studentInfo: Pick<IStudentInfo, 'id'>): number {
    return studentInfo.id;
  }

  compareStudentInfo(o1: Pick<IStudentInfo, 'id'> | null, o2: Pick<IStudentInfo, 'id'> | null): boolean {
    return o1 && o2 ? this.getStudentInfoIdentifier(o1) === this.getStudentInfoIdentifier(o2) : o1 === o2;
  }

  addStudentInfoToCollectionIfMissing<Type extends Pick<IStudentInfo, 'id'>>(
    studentInfoCollection: Type[],
    ...studentInfosToCheck: (Type | null | undefined)[]
  ): Type[] {
    const studentInfos: Type[] = studentInfosToCheck.filter(isPresent);
    if (studentInfos.length > 0) {
      const studentInfoCollectionIdentifiers = studentInfoCollection.map(studentInfoItem => this.getStudentInfoIdentifier(studentInfoItem));
      const studentInfosToAdd = studentInfos.filter(studentInfoItem => {
        const studentInfoIdentifier = this.getStudentInfoIdentifier(studentInfoItem);
        if (studentInfoCollectionIdentifiers.includes(studentInfoIdentifier)) {
          return false;
        }
        studentInfoCollectionIdentifiers.push(studentInfoIdentifier);
        return true;
      });
      return [...studentInfosToAdd, ...studentInfoCollection];
    }
    return studentInfoCollection;
  }
}
