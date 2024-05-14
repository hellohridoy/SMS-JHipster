package com.ridoy.student.web.rest;

import com.ridoy.student.domain.StudentInfo;
import com.ridoy.student.service.StudentInfoService;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.ridoy.student.domain.StudentInfo}.
 */
@RestController
@RequestMapping("/api/student-infos")
public class StudentInfoResource {

    private final Logger log = LoggerFactory.getLogger(StudentInfoResource.class);

    private final StudentInfoService studentInfoService;

    public StudentInfoResource(StudentInfoService studentInfoService) {
        this.studentInfoService = studentInfoService;
    }

    /**
     * {@code GET  /student-infos} : get all the studentInfos.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of studentInfos in body.
     */
    @GetMapping("")
    public List<StudentInfo> getAllStudentInfos() {
        log.debug("REST request to get all StudentInfos");
        return studentInfoService.findAll();
    }

    /**
     * {@code GET  /student-infos/:id} : get the "id" studentInfo.
     *
     * @param id the id of the studentInfo to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the studentInfo, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<StudentInfo> getStudentInfo(@PathVariable("id") Long id) {
        log.debug("REST request to get StudentInfo : {}", id);
        Optional<StudentInfo> studentInfo = studentInfoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(studentInfo);
    }
}
