package com.ridoy.student.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class StudentInfoTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static StudentInfo getStudentInfoSample1() {
        return new StudentInfo().id(1L).firstName("firstName1").lastName("lastName1").email("email1");
    }

    public static StudentInfo getStudentInfoSample2() {
        return new StudentInfo().id(2L).firstName("firstName2").lastName("lastName2").email("email2");
    }

    public static StudentInfo getStudentInfoRandomSampleGenerator() {
        return new StudentInfo()
            .id(longCount.incrementAndGet())
            .firstName(UUID.randomUUID().toString())
            .lastName(UUID.randomUUID().toString())
            .email(UUID.randomUUID().toString());
    }
}
