import { IStudentInfo } from './student-info.model';

export const sampleWithRequiredData: IStudentInfo = {
  id: 21486,
};

export const sampleWithPartialData: IStudentInfo = {
  id: 7537,
  lastName: 'Gutmann',
  email: 'Claudia_Wisozk16@yahoo.com',
  isStudent: true,
  status: 'INACTIVE',
};

export const sampleWithFullData: IStudentInfo = {
  id: 15098,
  firstName: 'Zion',
  lastName: 'Mills',
  email: 'Carole24@yahoo.com',
  isStudent: false,
  status: 'INACTIVE',
};
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
