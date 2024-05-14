package com.ridoy.student.domain;

import static com.ridoy.student.domain.StudentInfoTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.ridoy.student.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class StudentInfoTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(StudentInfo.class);
        StudentInfo studentInfo1 = getStudentInfoSample1();
        StudentInfo studentInfo2 = new StudentInfo();
        assertThat(studentInfo1).isNotEqualTo(studentInfo2);

        studentInfo2.setId(studentInfo1.getId());
        assertThat(studentInfo1).isEqualTo(studentInfo2);

        studentInfo2 = getStudentInfoSample2();
        assertThat(studentInfo1).isNotEqualTo(studentInfo2);
    }
}
