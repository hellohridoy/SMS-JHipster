package com.ridoy.student.web.rest;

import static com.ridoy.student.domain.StudentInfoAsserts.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ridoy.student.IntegrationTest;
import com.ridoy.student.domain.StudentInfo;
import com.ridoy.student.domain.enumeration.Status;
import com.ridoy.student.repository.StudentInfoRepository;
import jakarta.persistence.EntityManager;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link StudentInfoResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class StudentInfoResourceIT {

    private static final String DEFAULT_FIRST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_FIRST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_LAST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_LAST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    private static final Boolean DEFAULT_IS_STUDENT = false;
    private static final Boolean UPDATED_IS_STUDENT = true;

    private static final Status DEFAULT_STATUS = Status.ACTIVE;
    private static final Status UPDATED_STATUS = Status.INACTIVE;

    private static final String ENTITY_API_URL = "/api/student-infos";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private StudentInfoRepository studentInfoRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restStudentInfoMockMvc;

    private StudentInfo studentInfo;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static StudentInfo createEntity(EntityManager em) {
        StudentInfo studentInfo = new StudentInfo()
            .firstName(DEFAULT_FIRST_NAME)
            .lastName(DEFAULT_LAST_NAME)
            .email(DEFAULT_EMAIL)
            .isStudent(DEFAULT_IS_STUDENT)
            .status(DEFAULT_STATUS);
        return studentInfo;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static StudentInfo createUpdatedEntity(EntityManager em) {
        StudentInfo studentInfo = new StudentInfo()
            .firstName(UPDATED_FIRST_NAME)
            .lastName(UPDATED_LAST_NAME)
            .email(UPDATED_EMAIL)
            .isStudent(UPDATED_IS_STUDENT)
            .status(UPDATED_STATUS);
        return studentInfo;
    }

    @BeforeEach
    public void initTest() {
        studentInfo = createEntity(em);
    }

    @Test
    @Transactional
    void getAllStudentInfos() throws Exception {
        // Initialize the database
        studentInfoRepository.saveAndFlush(studentInfo);

        // Get all the studentInfoList
        restStudentInfoMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(studentInfo.getId().intValue())))
            .andExpect(jsonPath("$.[*].firstName").value(hasItem(DEFAULT_FIRST_NAME)))
            .andExpect(jsonPath("$.[*].lastName").value(hasItem(DEFAULT_LAST_NAME)))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)))
            .andExpect(jsonPath("$.[*].isStudent").value(hasItem(DEFAULT_IS_STUDENT.booleanValue())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())));
    }

    @Test
    @Transactional
    void getStudentInfo() throws Exception {
        // Initialize the database
        studentInfoRepository.saveAndFlush(studentInfo);

        // Get the studentInfo
        restStudentInfoMockMvc
            .perform(get(ENTITY_API_URL_ID, studentInfo.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(studentInfo.getId().intValue()))
            .andExpect(jsonPath("$.firstName").value(DEFAULT_FIRST_NAME))
            .andExpect(jsonPath("$.lastName").value(DEFAULT_LAST_NAME))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL))
            .andExpect(jsonPath("$.isStudent").value(DEFAULT_IS_STUDENT.booleanValue()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()));
    }

    @Test
    @Transactional
    void getNonExistingStudentInfo() throws Exception {
        // Get the studentInfo
        restStudentInfoMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    protected long getRepositoryCount() {
        return studentInfoRepository.count();
    }

    protected void assertIncrementedRepositoryCount(long countBefore) {
        assertThat(countBefore + 1).isEqualTo(getRepositoryCount());
    }

    protected void assertDecrementedRepositoryCount(long countBefore) {
        assertThat(countBefore - 1).isEqualTo(getRepositoryCount());
    }

    protected void assertSameRepositoryCount(long countBefore) {
        assertThat(countBefore).isEqualTo(getRepositoryCount());
    }

    protected StudentInfo getPersistedStudentInfo(StudentInfo studentInfo) {
        return studentInfoRepository.findById(studentInfo.getId()).orElseThrow();
    }

    protected void assertPersistedStudentInfoToMatchAllProperties(StudentInfo expectedStudentInfo) {
        assertStudentInfoAllPropertiesEquals(expectedStudentInfo, getPersistedStudentInfo(expectedStudentInfo));
    }

    protected void assertPersistedStudentInfoToMatchUpdatableProperties(StudentInfo expectedStudentInfo) {
        assertStudentInfoAllUpdatablePropertiesEquals(expectedStudentInfo, getPersistedStudentInfo(expectedStudentInfo));
    }
}
