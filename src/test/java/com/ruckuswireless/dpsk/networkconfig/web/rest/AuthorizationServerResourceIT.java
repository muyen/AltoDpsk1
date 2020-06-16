package com.ruckuswireless.dpsk.networkconfig.web.rest;

import com.ruckuswireless.dpsk.networkconfig.AltoDpsk1App;
import com.ruckuswireless.dpsk.networkconfig.domain.AuthorizationServer;
import com.ruckuswireless.dpsk.networkconfig.repository.AuthorizationServerRepository;
import com.ruckuswireless.dpsk.networkconfig.service.AuthorizationServerService;
import com.ruckuswireless.dpsk.networkconfig.service.dto.AuthorizationServerDTO;
import com.ruckuswireless.dpsk.networkconfig.service.mapper.AuthorizationServerMapper;

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
 * Integration tests for the {@link AuthorizationServerResource} REST controller.
 */
@SpringBootTest(classes = AltoDpsk1App.class)
@AutoConfigureMockMvc
@WithMockUser
public class AuthorizationServerResourceIT {

    @Autowired
    private AuthorizationServerRepository authorizationServerRepository;

    @Autowired
    private AuthorizationServerMapper authorizationServerMapper;

    @Autowired
    private AuthorizationServerService authorizationServerService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restAuthorizationServerMockMvc;

    private AuthorizationServer authorizationServer;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AuthorizationServer createEntity(EntityManager em) {
        AuthorizationServer authorizationServer = new AuthorizationServer();
        return authorizationServer;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AuthorizationServer createUpdatedEntity(EntityManager em) {
        AuthorizationServer authorizationServer = new AuthorizationServer();
        return authorizationServer;
    }

    @BeforeEach
    public void initTest() {
        authorizationServer = createEntity(em);
    }

    @Test
    @Transactional
    public void createAuthorizationServer() throws Exception {
        int databaseSizeBeforeCreate = authorizationServerRepository.findAll().size();
        // Create the AuthorizationServer
        AuthorizationServerDTO authorizationServerDTO = authorizationServerMapper.toDto(authorizationServer);
        restAuthorizationServerMockMvc.perform(post("/api/authorization-servers")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(authorizationServerDTO)))
            .andExpect(status().isCreated());

        // Validate the AuthorizationServer in the database
        List<AuthorizationServer> authorizationServerList = authorizationServerRepository.findAll();
        assertThat(authorizationServerList).hasSize(databaseSizeBeforeCreate + 1);
        AuthorizationServer testAuthorizationServer = authorizationServerList.get(authorizationServerList.size() - 1);
    }

    @Test
    @Transactional
    public void createAuthorizationServerWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = authorizationServerRepository.findAll().size();

        // Create the AuthorizationServer with an existing ID
        authorizationServer.setId(1L);
        AuthorizationServerDTO authorizationServerDTO = authorizationServerMapper.toDto(authorizationServer);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAuthorizationServerMockMvc.perform(post("/api/authorization-servers")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(authorizationServerDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AuthorizationServer in the database
        List<AuthorizationServer> authorizationServerList = authorizationServerRepository.findAll();
        assertThat(authorizationServerList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllAuthorizationServers() throws Exception {
        // Initialize the database
        authorizationServerRepository.saveAndFlush(authorizationServer);

        // Get all the authorizationServerList
        restAuthorizationServerMockMvc.perform(get("/api/authorization-servers?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(authorizationServer.getId().intValue())));
    }
    
    @Test
    @Transactional
    public void getAuthorizationServer() throws Exception {
        // Initialize the database
        authorizationServerRepository.saveAndFlush(authorizationServer);

        // Get the authorizationServer
        restAuthorizationServerMockMvc.perform(get("/api/authorization-servers/{id}", authorizationServer.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(authorizationServer.getId().intValue()));
    }
    @Test
    @Transactional
    public void getNonExistingAuthorizationServer() throws Exception {
        // Get the authorizationServer
        restAuthorizationServerMockMvc.perform(get("/api/authorization-servers/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAuthorizationServer() throws Exception {
        // Initialize the database
        authorizationServerRepository.saveAndFlush(authorizationServer);

        int databaseSizeBeforeUpdate = authorizationServerRepository.findAll().size();

        // Update the authorizationServer
        AuthorizationServer updatedAuthorizationServer = authorizationServerRepository.findById(authorizationServer.getId()).get();
        // Disconnect from session so that the updates on updatedAuthorizationServer are not directly saved in db
        em.detach(updatedAuthorizationServer);
        AuthorizationServerDTO authorizationServerDTO = authorizationServerMapper.toDto(updatedAuthorizationServer);

        restAuthorizationServerMockMvc.perform(put("/api/authorization-servers")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(authorizationServerDTO)))
            .andExpect(status().isOk());

        // Validate the AuthorizationServer in the database
        List<AuthorizationServer> authorizationServerList = authorizationServerRepository.findAll();
        assertThat(authorizationServerList).hasSize(databaseSizeBeforeUpdate);
        AuthorizationServer testAuthorizationServer = authorizationServerList.get(authorizationServerList.size() - 1);
    }

    @Test
    @Transactional
    public void updateNonExistingAuthorizationServer() throws Exception {
        int databaseSizeBeforeUpdate = authorizationServerRepository.findAll().size();

        // Create the AuthorizationServer
        AuthorizationServerDTO authorizationServerDTO = authorizationServerMapper.toDto(authorizationServer);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAuthorizationServerMockMvc.perform(put("/api/authorization-servers")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(authorizationServerDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AuthorizationServer in the database
        List<AuthorizationServer> authorizationServerList = authorizationServerRepository.findAll();
        assertThat(authorizationServerList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteAuthorizationServer() throws Exception {
        // Initialize the database
        authorizationServerRepository.saveAndFlush(authorizationServer);

        int databaseSizeBeforeDelete = authorizationServerRepository.findAll().size();

        // Delete the authorizationServer
        restAuthorizationServerMockMvc.perform(delete("/api/authorization-servers/{id}", authorizationServer.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<AuthorizationServer> authorizationServerList = authorizationServerRepository.findAll();
        assertThat(authorizationServerList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
