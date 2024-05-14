import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { of, EMPTY, Observable } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IStudentInfo } from '../student-info.model';
import { StudentInfoService } from '../service/student-info.service';

const studentInfoResolve = (route: ActivatedRouteSnapshot): Observable<null | IStudentInfo> => {
  const id = route.params['id'];
  if (id) {
    return inject(StudentInfoService)
      .find(id)
      .pipe(
        mergeMap((studentInfo: HttpResponse<IStudentInfo>) => {
          if (studentInfo.body) {
            return of(studentInfo.body);
          } else {
            inject(Router).navigate(['404']);
            return EMPTY;
          }
        }),
      );
  }
  return of(null);
};

export default studentInfoResolve;
