package br.gov.mec.polen.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import br.gov.mec.polen.IntegrationTest;
import br.gov.mec.polen.domain.EfficiencyResult;
import br.gov.mec.polen.repository.EfficiencyResultRepository;
import br.gov.mec.polen.service.criteria.EfficiencyResultCriteria;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link EfficiencyResultResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class EfficiencyResultResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/efficiency-results";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private EfficiencyResultRepository efficiencyResultRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restEfficiencyResultMockMvc;

    private EfficiencyResult efficiencyResult;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EfficiencyResult createEntity(EntityManager em) {
        EfficiencyResult efficiencyResult = new EfficiencyResult().name(DEFAULT_NAME);
        return efficiencyResult;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EfficiencyResult createUpdatedEntity(EntityManager em) {
        EfficiencyResult efficiencyResult = new EfficiencyResult().name(UPDATED_NAME);
        return efficiencyResult;
    }

    @BeforeEach
    public void initTest() {
        efficiencyResult = createEntity(em);
    }

    @Test
    @Transactional
    void getAllEfficiencyResults() throws Exception {
        // Initialize the database
        efficiencyResultRepository.saveAndFlush(efficiencyResult);

        // Get all the efficiencyResultList
        restEfficiencyResultMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(efficiencyResult.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)));
    }

    @Test
    @Transactional
    void getEfficiencyResult() throws Exception {
        // Initialize the database
        efficiencyResultRepository.saveAndFlush(efficiencyResult);

        // Get the efficiencyResult
        restEfficiencyResultMockMvc
            .perform(get(ENTITY_API_URL_ID, efficiencyResult.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(efficiencyResult.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME));
    }

    @Test
    @Transactional
    void getEfficiencyResultsByIdFiltering() throws Exception {
        // Initialize the database
        efficiencyResultRepository.saveAndFlush(efficiencyResult);

        Long id = efficiencyResult.getId();

        defaultEfficiencyResultShouldBeFound("id.equals=" + id);
        defaultEfficiencyResultShouldNotBeFound("id.notEquals=" + id);

        defaultEfficiencyResultShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultEfficiencyResultShouldNotBeFound("id.greaterThan=" + id);

        defaultEfficiencyResultShouldBeFound("id.lessThanOrEqual=" + id);
        defaultEfficiencyResultShouldNotBeFound("id.lessThan=" + id);
    }

    @Test
    @Transactional
    void getAllEfficiencyResultsByNameIsEqualToSomething() throws Exception {
        // Initialize the database
        efficiencyResultRepository.saveAndFlush(efficiencyResult);

        // Get all the efficiencyResultList where name equals to DEFAULT_NAME
        defaultEfficiencyResultShouldBeFound("name.equals=" + DEFAULT_NAME);

        // Get all the efficiencyResultList where name equals to UPDATED_NAME
        defaultEfficiencyResultShouldNotBeFound("name.equals=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    void getAllEfficiencyResultsByNameIsNotEqualToSomething() throws Exception {
        // Initialize the database
        efficiencyResultRepository.saveAndFlush(efficiencyResult);

        // Get all the efficiencyResultList where name not equals to DEFAULT_NAME
        defaultEfficiencyResultShouldNotBeFound("name.notEquals=" + DEFAULT_NAME);

        // Get all the efficiencyResultList where name not equals to UPDATED_NAME
        defaultEfficiencyResultShouldBeFound("name.notEquals=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    void getAllEfficiencyResultsByNameIsInShouldWork() throws Exception {
        // Initialize the database
        efficiencyResultRepository.saveAndFlush(efficiencyResult);

        // Get all the efficiencyResultList where name in DEFAULT_NAME or UPDATED_NAME
        defaultEfficiencyResultShouldBeFound("name.in=" + DEFAULT_NAME + "," + UPDATED_NAME);

        // Get all the efficiencyResultList where name equals to UPDATED_NAME
        defaultEfficiencyResultShouldNotBeFound("name.in=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    void getAllEfficiencyResultsByNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        efficiencyResultRepository.saveAndFlush(efficiencyResult);

        // Get all the efficiencyResultList where name is not null
        defaultEfficiencyResultShouldBeFound("name.specified=true");

        // Get all the efficiencyResultList where name is null
        defaultEfficiencyResultShouldNotBeFound("name.specified=false");
    }

    @Test
    @Transactional
    void getAllEfficiencyResultsByNameContainsSomething() throws Exception {
        // Initialize the database
        efficiencyResultRepository.saveAndFlush(efficiencyResult);

        // Get all the efficiencyResultList where name contains DEFAULT_NAME
        defaultEfficiencyResultShouldBeFound("name.contains=" + DEFAULT_NAME);

        // Get all the efficiencyResultList where name contains UPDATED_NAME
        defaultEfficiencyResultShouldNotBeFound("name.contains=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    void getAllEfficiencyResultsByNameNotContainsSomething() throws Exception {
        // Initialize the database
        efficiencyResultRepository.saveAndFlush(efficiencyResult);

        // Get all the efficiencyResultList where name does not contain DEFAULT_NAME
        defaultEfficiencyResultShouldNotBeFound("name.doesNotContain=" + DEFAULT_NAME);

        // Get all the efficiencyResultList where name does not contain UPDATED_NAME
        defaultEfficiencyResultShouldBeFound("name.doesNotContain=" + UPDATED_NAME);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultEfficiencyResultShouldBeFound(String filter) throws Exception {
        restEfficiencyResultMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(efficiencyResult.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)));

        // Check, that the count call also returns 1
        restEfficiencyResultMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultEfficiencyResultShouldNotBeFound(String filter) throws Exception {
        restEfficiencyResultMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restEfficiencyResultMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    void getNonExistingEfficiencyResult() throws Exception {
        // Get the efficiencyResult
        restEfficiencyResultMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }
}
