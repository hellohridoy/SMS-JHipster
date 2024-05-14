import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
import { StudentInfoComponent } from './list/student-info.component';
import { StudentInfoDetailComponent } from './detail/student-info-detail.component';
import StudentInfoResolve from './route/student-info-routing-resolve.service';

const studentInfoRoute: Routes = [
  {
    path: '',
    component: StudentInfoComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: StudentInfoDetailComponent,
    resolve: {
      studentInfo: StudentInfoResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default studentInfoRoute;
