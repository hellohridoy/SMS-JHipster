package com.ridoy.student.service.impl;

import com.ridoy.student.domain.StudentInfo;
import com.ridoy.student.repository.StudentInfoRepository;
import com.ridoy.student.service.StudentInfoService;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.ridoy.student.domain.StudentInfo}.
 */
@Service
@Transactional
public class StudentInfoServiceImpl implements StudentInfoService {

    private final Logger log = LoggerFactory.getLogger(StudentInfoServiceImpl.class);

    private final StudentInfoRepository studentInfoRepository;

    public StudentInfoServiceImpl(StudentInfoRepository studentInfoRepository) {
        this.studentInfoRepository = studentInfoRepository;
    }

    @Override
    public StudentInfo save(StudentInfo studentInfo) {
        log.debug("Request to save StudentInfo : {}", studentInfo);
        return studentInfoRepository.save(studentInfo);
    }

    @Override
    public StudentInfo update(StudentInfo studentInfo) {
        log.debug("Request to update StudentInfo : {}", studentInfo);
        return studentInfoRepository.save(studentInfo);
    }

    @Override
    public Optional<StudentInfo> partialUpdate(StudentInfo studentInfo) {
        log.debug("Request to partially update StudentInfo : {}", studentInfo);

        return studentInfoRepository
            .findById(studentInfo.getId())
            .map(existingStudentInfo -> {
                if (studentInfo.getFirstName() != null) {
                    existingStudentInfo.setFirstName(studentInfo.getFirstName());
                }
                if (studentInfo.getLastName() != null) {
                    existingStudentInfo.setLastName(studentInfo.getLastName());
                }
                if (studentInfo.getEmail() != null) {
                    existingStudentInfo.setEmail(studentInfo.getEmail());
                }
                if (studentInfo.getIsStudent() != null) {
                    existingStudentInfo.setIsStudent(studentInfo.getIsStudent());
                }
                if (studentInfo.getStatus() != null) {
                    existingStudentInfo.setStatus(studentInfo.getStatus());
                }
                if (studentInfo.getCgpa() != null) {
                    existingStudentInfo.setCgpa(studentInfo.getCgpa());
                }

                return existingStudentInfo;
            })
            .map(studentInfoRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<StudentInfo> findAll() {
        log.debug("Request to get all StudentInfos");
        return studentInfoRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<StudentInfo> findOne(Long id) {
        log.debug("Request to get StudentInfo : {}", id);
        return studentInfoRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete StudentInfo : {}", id);
        studentInfoRepository.deleteById(id);
    }
}
