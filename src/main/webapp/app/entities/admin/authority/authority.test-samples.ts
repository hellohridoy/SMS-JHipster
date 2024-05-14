import { IAuthority, NewAuthority } from './authority.model';

export const sampleWithRequiredData: IAuthority = {
  name: '9398364f-4446-48ed-9726-7b3a73630b01',
};

export const sampleWithPartialData: IAuthority = {
  name: '6c04384e-3498-43aa-b437-81e7fd810c5c',
};

export const sampleWithFullData: IAuthority = {
  name: '7bbfc98b-355c-4c43-b6f9-d525ebae5dde',
};

export const sampleWithNewData: NewAuthority = {
  name: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
