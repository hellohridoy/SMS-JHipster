package com.ridoy.student.repository;

import com.ridoy.student.domain.StudentInfo;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the StudentInfo entity.
 */
@SuppressWarnings("unused")
@Repository
public interface StudentInfoRepository extends JpaRepository<StudentInfo, Long> {}
