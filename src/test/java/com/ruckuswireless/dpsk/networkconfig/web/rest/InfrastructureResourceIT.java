package com.ruckuswireless.dpsk.networkconfig.web.rest;

import com.ruckuswireless.dpsk.networkconfig.AltoDpsk1App;
import com.ruckuswireless.dpsk.networkconfig.domain.Infrastructure;
import com.ruckuswireless.dpsk.networkconfig.repository.InfrastructureRepository;
import com.ruckuswireless.dpsk.networkconfig.service.InfrastructureService;
import com.ruckuswireless.dpsk.networkconfig.service.dto.InfrastructureDTO;
import com.ruckuswireless.dpsk.networkconfig.service.mapper.InfrastructureMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link InfrastructureResource} REST controller.
 */
@SpringBootTest(classes = AltoDpsk1App.class)
@AutoConfigureMockMvc
@WithMockUser
public class InfrastructureResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    @Autowired
    private InfrastructureRepository infrastructureRepository;

    @Autowired
    private InfrastructureMapper infrastructureMapper;

    @Autowired
    private InfrastructureService infrastructureService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restInfrastructureMockMvc;

    private Infrastructure infrastructure;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Infrastructure createEntity(EntityManager em) {
        Infrastructure infrastructure = new Infrastructure()
            .name(DEFAULT_NAME);
        return infrastructure;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Infrastructure createUpdatedEntity(EntityManager em) {
        Infrastructure infrastructure = new Infrastructure()
            .name(UPDATED_NAME);
        return infrastructure;
    }

    @BeforeEach
    public void initTest() {
        infrastructure = createEntity(em);
    }

    @Test
    @Transactional
    public void createInfrastructure() throws Exception {
        int databaseSizeBeforeCreate = infrastructureRepository.findAll().size();
        // Create the Infrastructure
        InfrastructureDTO infrastructureDTO = infrastructureMapper.toDto(infrastructure);
        restInfrastructureMockMvc.perform(post("/api/infrastructures")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(infrastructureDTO)))
            .andExpect(status().isCreated());

        // Validate the Infrastructure in the database
        List<Infrastructure> infrastructureList = infrastructureRepository.findAll();
        assertThat(infrastructureList).hasSize(databaseSizeBeforeCreate + 1);
        Infrastructure testInfrastructure = infrastructureList.get(infrastructureList.size() - 1);
        assertThat(testInfrastructure.getName()).isEqualTo(DEFAULT_NAME);
    }

    @Test
    @Transactional
    public void createInfrastructureWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = infrastructureRepository.findAll().size();

        // Create the Infrastructure with an existing ID
        infrastructure.setId(1L);
        InfrastructureDTO infrastructureDTO = infrastructureMapper.toDto(infrastructure);

        // An entity with an existing ID cannot be created, so this API call must fail
        restInfrastructureMockMvc.perform(post("/api/infrastructures")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(infrastructureDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Infrastructure in the database
        List<Infrastructure> infrastructureList = infrastructureRepository.findAll();
        assertThat(infrastructureList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllInfrastructures() throws Exception {
        // Initialize the database
        infrastructureRepository.saveAndFlush(infrastructure);

        // Get all the infrastructureList
        restInfrastructureMockMvc.perform(get("/api/infrastructures?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(infrastructure.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)));
    }
    
    @Test
    @Transactional
    public void getInfrastructure() throws Exception {
        // Initialize the database
        infrastructureRepository.saveAndFlush(infrastructure);

        // Get the infrastructure
        restInfrastructureMockMvc.perform(get("/api/infrastructures/{id}", infrastructure.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(infrastructure.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME));
    }
    @Test
    @Transactional
    public void getNonExistingInfrastructure() throws Exception {
        // Get the infrastructure
        restInfrastructureMockMvc.perform(get("/api/infrastructures/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateInfrastructure() throws Exception {
        // Initialize the database
        infrastructureRepository.saveAndFlush(infrastructure);

        int databaseSizeBeforeUpdate = infrastructureRepository.findAll().size();

        // Update the infrastructure
        Infrastructure updatedInfrastructure = infrastructureRepository.findById(infrastructure.getId()).get();
        // Disconnect from session so that the updates on updatedInfrastructure are not directly saved in db
        em.detach(updatedInfrastructure);
        updatedInfrastructure
            .name(UPDATED_NAME);
        InfrastructureDTO infrastructureDTO = infrastructureMapper.toDto(updatedInfrastructure);

        restInfrastructureMockMvc.perform(put("/api/infrastructures")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(infrastructureDTO)))
            .andExpect(status().isOk());

        // Validate the Infrastructure in the database
        List<Infrastructure> infrastructureList = infrastructureRepository.findAll();
        assertThat(infrastructureList).hasSize(databaseSizeBeforeUpdate);
        Infrastructure testInfrastructure = infrastructureList.get(infrastructureList.size() - 1);
        assertThat(testInfrastructure.getName()).isEqualTo(UPDATED_NAME);
    }

    @Test
    @Transactional
    public void updateNonExistingInfrastructure() throws Exception {
        int databaseSizeBeforeUpdate = infrastructureRepository.findAll().size();

        // Create the Infrastructure
        InfrastructureDTO infrastructureDTO = infrastructureMapper.toDto(infrastructure);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restInfrastructureMockMvc.perform(put("/api/infrastructures")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(infrastructureDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Infrastructure in the database
        List<Infrastructure> infrastructureList = infrastructureRepository.findAll();
        assertThat(infrastructureList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteInfrastructure() throws Exception {
        // Initialize the database
        infrastructureRepository.saveAndFlush(infrastructure);

        int databaseSizeBeforeDelete = infrastructureRepository.findAll().size();

        // Delete the infrastructure
        restInfrastructureMockMvc.perform(delete("/api/infrastructures/{id}", infrastructure.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Infrastructure> infrastructureList = infrastructureRepository.findAll();
        assertThat(infrastructureList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
