package com.ridoy.student.service;

import com.ridoy.student.domain.StudentInfo;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.ridoy.student.domain.StudentInfo}.
 */
public interface StudentInfoService {
    /**
     * Save a studentInfo.
     *
     * @param studentInfo the entity to save.
     * @return the persisted entity.
     */
    StudentInfo save(StudentInfo studentInfo);

    /**
     * Updates a studentInfo.
     *
     * @param studentInfo the entity to update.
     * @return the persisted entity.
     */
    StudentInfo update(StudentInfo studentInfo);

    /**
     * Partially updates a studentInfo.
     *
     * @param studentInfo the entity to update partially.
     * @return the persisted entity.
     */
    Optional<StudentInfo> partialUpdate(StudentInfo studentInfo);

    /**
     * Get all the studentInfos.
     *
     * @return the list of entities.
     */
    List<StudentInfo> findAll();

    /**
     * Get the "id" studentInfo.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<StudentInfo> findOne(Long id);

    /**
     * Delete the "id" studentInfo.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
