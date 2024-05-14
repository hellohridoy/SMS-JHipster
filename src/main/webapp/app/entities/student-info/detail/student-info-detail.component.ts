import { Component, input } from '@angular/core';
import { RouterModule } from '@angular/router';

import SharedModule from 'app/shared/shared.module';
import { DurationPipe, FormatMediumDatetimePipe, FormatMediumDatePipe } from 'app/shared/date';
import { IStudentInfo } from '../student-info.model';

@Component({
  standalone: true,
  selector: 'jhi-student-info-detail',
  templateUrl: './student-info-detail.component.html',
  imports: [SharedModule, RouterModule, DurationPipe, FormatMediumDatetimePipe, FormatMediumDatePipe],
})
export class StudentInfoDetailComponent {
  studentInfo = input<IStudentInfo | null>(null);

  previousState(): void {
    window.history.back();
  }
}
