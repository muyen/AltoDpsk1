package com.ruckuswireless.dpsk.networkconfig.web.rest;

import com.ruckuswireless.dpsk.networkconfig.AltoDpsk1App;
import com.ruckuswireless.dpsk.networkconfig.domain.AuthConfig;
import com.ruckuswireless.dpsk.networkconfig.repository.AuthConfigRepository;
import com.ruckuswireless.dpsk.networkconfig.service.AuthConfigService;
import com.ruckuswireless.dpsk.networkconfig.service.dto.AuthConfigDTO;
import com.ruckuswireless.dpsk.networkconfig.service.mapper.AuthConfigMapper;

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
 * Integration tests for the {@link AuthConfigResource} REST controller.
 */
@SpringBootTest(classes = AltoDpsk1App.class)
@AutoConfigureMockMvc
@WithMockUser
public class AuthConfigResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_IP_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_IP_ADDRESS = "BBBBBBBBBB";

    private static final Integer DEFAULT_PORT = 1;
    private static final Integer UPDATED_PORT = 2;

    private static final String DEFAULT_SHARED_SECRET = "AAAAAAAAAA";
    private static final String UPDATED_SHARED_SECRET = "BBBBBBBBBB";

    @Autowired
    private AuthConfigRepository authConfigRepository;

    @Autowired
    private AuthConfigMapper authConfigMapper;

    @Autowired
    private AuthConfigService authConfigService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restAuthConfigMockMvc;

    private AuthConfig authConfig;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AuthConfig createEntity(EntityManager em) {
        AuthConfig authConfig = new AuthConfig()
            .name(DEFAULT_NAME)
            .ipAddress(DEFAULT_IP_ADDRESS)
            .port(DEFAULT_PORT)
            .sharedSecret(DEFAULT_SHARED_SECRET);
        return authConfig;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AuthConfig createUpdatedEntity(EntityManager em) {
        AuthConfig authConfig = new AuthConfig()
            .name(UPDATED_NAME)
            .ipAddress(UPDATED_IP_ADDRESS)
            .port(UPDATED_PORT)
            .sharedSecret(UPDATED_SHARED_SECRET);
        return authConfig;
    }

    @BeforeEach
    public void initTest() {
        authConfig = createEntity(em);
    }

    @Test
    @Transactional
    public void createAuthConfig() throws Exception {
        int databaseSizeBeforeCreate = authConfigRepository.findAll().size();
        // Create the AuthConfig
        AuthConfigDTO authConfigDTO = authConfigMapper.toDto(authConfig);
        restAuthConfigMockMvc.perform(post("/api/auth-configs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(authConfigDTO)))
            .andExpect(status().isCreated());

        // Validate the AuthConfig in the database
        List<AuthConfig> authConfigList = authConfigRepository.findAll();
        assertThat(authConfigList).hasSize(databaseSizeBeforeCreate + 1);
        AuthConfig testAuthConfig = authConfigList.get(authConfigList.size() - 1);
        assertThat(testAuthConfig.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testAuthConfig.getIpAddress()).isEqualTo(DEFAULT_IP_ADDRESS);
        assertThat(testAuthConfig.getPort()).isEqualTo(DEFAULT_PORT);
        assertThat(testAuthConfig.getSharedSecret()).isEqualTo(DEFAULT_SHARED_SECRET);
    }

    @Test
    @Transactional
    public void createAuthConfigWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = authConfigRepository.findAll().size();

        // Create the AuthConfig with an existing ID
        authConfig.setId(1L);
        AuthConfigDTO authConfigDTO = authConfigMapper.toDto(authConfig);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAuthConfigMockMvc.perform(post("/api/auth-configs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(authConfigDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AuthConfig in the database
        List<AuthConfig> authConfigList = authConfigRepository.findAll();
        assertThat(authConfigList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllAuthConfigs() throws Exception {
        // Initialize the database
        authConfigRepository.saveAndFlush(authConfig);

        // Get all the authConfigList
        restAuthConfigMockMvc.perform(get("/api/auth-configs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(authConfig.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].ipAddress").value(hasItem(DEFAULT_IP_ADDRESS)))
            .andExpect(jsonPath("$.[*].port").value(hasItem(DEFAULT_PORT)))
            .andExpect(jsonPath("$.[*].sharedSecret").value(hasItem(DEFAULT_SHARED_SECRET)));
    }
    
    @Test
    @Transactional
    public void getAuthConfig() throws Exception {
        // Initialize the database
        authConfigRepository.saveAndFlush(authConfig);

        // Get the authConfig
        restAuthConfigMockMvc.perform(get("/api/auth-configs/{id}", authConfig.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(authConfig.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.ipAddress").value(DEFAULT_IP_ADDRESS))
            .andExpect(jsonPath("$.port").value(DEFAULT_PORT))
            .andExpect(jsonPath("$.sharedSecret").value(DEFAULT_SHARED_SECRET));
    }
    @Test
    @Transactional
    public void getNonExistingAuthConfig() throws Exception {
        // Get the authConfig
        restAuthConfigMockMvc.perform(get("/api/auth-configs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAuthConfig() throws Exception {
        // Initialize the database
        authConfigRepository.saveAndFlush(authConfig);

        int databaseSizeBeforeUpdate = authConfigRepository.findAll().size();

        // Update the authConfig
        AuthConfig updatedAuthConfig = authConfigRepository.findById(authConfig.getId()).get();
        // Disconnect from session so that the updates on updatedAuthConfig are not directly saved in db
        em.detach(updatedAuthConfig);
        updatedAuthConfig
            .name(UPDATED_NAME)
            .ipAddress(UPDATED_IP_ADDRESS)
            .port(UPDATED_PORT)
            .sharedSecret(UPDATED_SHARED_SECRET);
        AuthConfigDTO authConfigDTO = authConfigMapper.toDto(updatedAuthConfig);

        restAuthConfigMockMvc.perform(put("/api/auth-configs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(authConfigDTO)))
            .andExpect(status().isOk());

        // Validate the AuthConfig in the database
        List<AuthConfig> authConfigList = authConfigRepository.findAll();
        assertThat(authConfigList).hasSize(databaseSizeBeforeUpdate);
        AuthConfig testAuthConfig = authConfigList.get(authConfigList.size() - 1);
        assertThat(testAuthConfig.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testAuthConfig.getIpAddress()).isEqualTo(UPDATED_IP_ADDRESS);
        assertThat(testAuthConfig.getPort()).isEqualTo(UPDATED_PORT);
        assertThat(testAuthConfig.getSharedSecret()).isEqualTo(UPDATED_SHARED_SECRET);
    }

    @Test
    @Transactional
    public void updateNonExistingAuthConfig() throws Exception {
        int databaseSizeBeforeUpdate = authConfigRepository.findAll().size();

        // Create the AuthConfig
        AuthConfigDTO authConfigDTO = authConfigMapper.toDto(authConfig);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAuthConfigMockMvc.perform(put("/api/auth-configs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(authConfigDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AuthConfig in the database
        List<AuthConfig> authConfigList = authConfigRepository.findAll();
        assertThat(authConfigList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteAuthConfig() throws Exception {
        // Initialize the database
        authConfigRepository.saveAndFlush(authConfig);

        int databaseSizeBeforeDelete = authConfigRepository.findAll().size();

        // Delete the authConfig
        restAuthConfigMockMvc.perform(delete("/api/auth-configs/{id}", authConfig.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<AuthConfig> authConfigList = authConfigRepository.findAll();
        assertThat(authConfigList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
