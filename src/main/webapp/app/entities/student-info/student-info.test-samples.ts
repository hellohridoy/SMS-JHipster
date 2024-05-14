import { IStudentInfo } from './student-info.model';

export const sampleWithRequiredData: IStudentInfo = {
  id: 29539,
};

export const sampleWithPartialData: IStudentInfo = {
  id: 18167,
  firstName: 'Cassandra',
  email: 'Hipolito44@yahoo.com',
  cgpa: 5259.47,
};

export const sampleWithFullData: IStudentInfo = {
  id: 7193,
  firstName: 'Mathilde',
  lastName: 'Boehm',
  email: 'Reyna99@gmail.com',
  isStudent: false,
  status: 'ACTIVE',
  cgpa: 20706.86,
};
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
