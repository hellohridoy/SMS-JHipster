import { Status } from 'app/entities/enumerations/status.model';

export interface IStudentInfo {
  id: number;
  firstName?: string | null;
  lastName?: string | null;
  email?: string | null;
  isStudent?: boolean | null;
  status?: keyof typeof Status | null;
  cgpa?: number | null;
}
