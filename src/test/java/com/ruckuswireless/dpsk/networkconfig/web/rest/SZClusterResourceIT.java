package com.ruckuswireless.dpsk.networkconfig.web.rest;

import com.ruckuswireless.dpsk.networkconfig.AltoDpsk1App;
import com.ruckuswireless.dpsk.networkconfig.domain.SZCluster;
import com.ruckuswireless.dpsk.networkconfig.repository.SZClusterRepository;
import com.ruckuswireless.dpsk.networkconfig.service.SZClusterService;
import com.ruckuswireless.dpsk.networkconfig.service.dto.SZClusterDTO;
import com.ruckuswireless.dpsk.networkconfig.service.mapper.SZClusterMapper;

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
 * Integration tests for the {@link SZClusterResource} REST controller.
 */
@SpringBootTest(classes = AltoDpsk1App.class)
@AutoConfigureMockMvc
@WithMockUser
public class SZClusterResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_SHARED_SECRET = "AAAAAAAAAA";
    private static final String UPDATED_SHARED_SECRET = "BBBBBBBBBB";

    @Autowired
    private SZClusterRepository sZClusterRepository;

    @Autowired
    private SZClusterMapper sZClusterMapper;

    @Autowired
    private SZClusterService sZClusterService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restSZClusterMockMvc;

    private SZCluster sZCluster;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SZCluster createEntity(EntityManager em) {
        SZCluster sZCluster = new SZCluster()
            .name(DEFAULT_NAME)
            .sharedSecret(DEFAULT_SHARED_SECRET);
        return sZCluster;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SZCluster createUpdatedEntity(EntityManager em) {
        SZCluster sZCluster = new SZCluster()
            .name(UPDATED_NAME)
            .sharedSecret(UPDATED_SHARED_SECRET);
        return sZCluster;
    }

    @BeforeEach
    public void initTest() {
        sZCluster = createEntity(em);
    }

    @Test
    @Transactional
    public void createSZCluster() throws Exception {
        int databaseSizeBeforeCreate = sZClusterRepository.findAll().size();
        // Create the SZCluster
        SZClusterDTO sZClusterDTO = sZClusterMapper.toDto(sZCluster);
        restSZClusterMockMvc.perform(post("/api/sz-clusters")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(sZClusterDTO)))
            .andExpect(status().isCreated());

        // Validate the SZCluster in the database
        List<SZCluster> sZClusterList = sZClusterRepository.findAll();
        assertThat(sZClusterList).hasSize(databaseSizeBeforeCreate + 1);
        SZCluster testSZCluster = sZClusterList.get(sZClusterList.size() - 1);
        assertThat(testSZCluster.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testSZCluster.getSharedSecret()).isEqualTo(DEFAULT_SHARED_SECRET);
    }

    @Test
    @Transactional
    public void createSZClusterWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = sZClusterRepository.findAll().size();

        // Create the SZCluster with an existing ID
        sZCluster.setId(1L);
        SZClusterDTO sZClusterDTO = sZClusterMapper.toDto(sZCluster);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSZClusterMockMvc.perform(post("/api/sz-clusters")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(sZClusterDTO)))
            .andExpect(status().isBadRequest());

        // Validate the SZCluster in the database
        List<SZCluster> sZClusterList = sZClusterRepository.findAll();
        assertThat(sZClusterList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllSZClusters() throws Exception {
        // Initialize the database
        sZClusterRepository.saveAndFlush(sZCluster);

        // Get all the sZClusterList
        restSZClusterMockMvc.perform(get("/api/sz-clusters?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(sZCluster.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].sharedSecret").value(hasItem(DEFAULT_SHARED_SECRET)));
    }
    
    @Test
    @Transactional
    public void getSZCluster() throws Exception {
        // Initialize the database
        sZClusterRepository.saveAndFlush(sZCluster);

        // Get the sZCluster
        restSZClusterMockMvc.perform(get("/api/sz-clusters/{id}", sZCluster.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(sZCluster.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.sharedSecret").value(DEFAULT_SHARED_SECRET));
    }
    @Test
    @Transactional
    public void getNonExistingSZCluster() throws Exception {
        // Get the sZCluster
        restSZClusterMockMvc.perform(get("/api/sz-clusters/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSZCluster() throws Exception {
        // Initialize the database
        sZClusterRepository.saveAndFlush(sZCluster);

        int databaseSizeBeforeUpdate = sZClusterRepository.findAll().size();

        // Update the sZCluster
        SZCluster updatedSZCluster = sZClusterRepository.findById(sZCluster.getId()).get();
        // Disconnect from session so that the updates on updatedSZCluster are not directly saved in db
        em.detach(updatedSZCluster);
        updatedSZCluster
            .name(UPDATED_NAME)
            .sharedSecret(UPDATED_SHARED_SECRET);
        SZClusterDTO sZClusterDTO = sZClusterMapper.toDto(updatedSZCluster);

        restSZClusterMockMvc.perform(put("/api/sz-clusters")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(sZClusterDTO)))
            .andExpect(status().isOk());

        // Validate the SZCluster in the database
        List<SZCluster> sZClusterList = sZClusterRepository.findAll();
        assertThat(sZClusterList).hasSize(databaseSizeBeforeUpdate);
        SZCluster testSZCluster = sZClusterList.get(sZClusterList.size() - 1);
        assertThat(testSZCluster.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testSZCluster.getSharedSecret()).isEqualTo(UPDATED_SHARED_SECRET);
    }

    @Test
    @Transactional
    public void updateNonExistingSZCluster() throws Exception {
        int databaseSizeBeforeUpdate = sZClusterRepository.findAll().size();

        // Create the SZCluster
        SZClusterDTO sZClusterDTO = sZClusterMapper.toDto(sZCluster);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSZClusterMockMvc.perform(put("/api/sz-clusters")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(sZClusterDTO)))
            .andExpect(status().isBadRequest());

        // Validate the SZCluster in the database
        List<SZCluster> sZClusterList = sZClusterRepository.findAll();
        assertThat(sZClusterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSZCluster() throws Exception {
        // Initialize the database
        sZClusterRepository.saveAndFlush(sZCluster);

        int databaseSizeBeforeDelete = sZClusterRepository.findAll().size();

        // Delete the sZCluster
        restSZClusterMockMvc.perform(delete("/api/sz-clusters/{id}", sZCluster.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<SZCluster> sZClusterList = sZClusterRepository.findAll();
        assertThat(sZClusterList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
