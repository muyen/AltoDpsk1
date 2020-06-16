package com.ruckuswireless.dpsk.networkconfig.web.rest;

import com.ruckuswireless.dpsk.networkconfig.AltoDpsk1App;
import com.ruckuswireless.dpsk.networkconfig.domain.IpAddress;
import com.ruckuswireless.dpsk.networkconfig.repository.IpAddressRepository;
import com.ruckuswireless.dpsk.networkconfig.service.IpAddressService;
import com.ruckuswireless.dpsk.networkconfig.service.dto.IpAddressDTO;
import com.ruckuswireless.dpsk.networkconfig.service.mapper.IpAddressMapper;

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
 * Integration tests for the {@link IpAddressResource} REST controller.
 */
@SpringBootTest(classes = AltoDpsk1App.class)
@AutoConfigureMockMvc
@WithMockUser
public class IpAddressResourceIT {

    private static final String DEFAULT_IP_ADRESS = "AAAAAAAAAA";
    private static final String UPDATED_IP_ADRESS = "BBBBBBBBBB";

    @Autowired
    private IpAddressRepository ipAddressRepository;

    @Autowired
    private IpAddressMapper ipAddressMapper;

    @Autowired
    private IpAddressService ipAddressService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restIpAddressMockMvc;

    private IpAddress ipAddress;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static IpAddress createEntity(EntityManager em) {
        IpAddress ipAddress = new IpAddress()
            .ipAdress(DEFAULT_IP_ADRESS);
        return ipAddress;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static IpAddress createUpdatedEntity(EntityManager em) {
        IpAddress ipAddress = new IpAddress()
            .ipAdress(UPDATED_IP_ADRESS);
        return ipAddress;
    }

    @BeforeEach
    public void initTest() {
        ipAddress = createEntity(em);
    }

    @Test
    @Transactional
    public void createIpAddress() throws Exception {
        int databaseSizeBeforeCreate = ipAddressRepository.findAll().size();
        // Create the IpAddress
        IpAddressDTO ipAddressDTO = ipAddressMapper.toDto(ipAddress);
        restIpAddressMockMvc.perform(post("/api/ip-addresses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ipAddressDTO)))
            .andExpect(status().isCreated());

        // Validate the IpAddress in the database
        List<IpAddress> ipAddressList = ipAddressRepository.findAll();
        assertThat(ipAddressList).hasSize(databaseSizeBeforeCreate + 1);
        IpAddress testIpAddress = ipAddressList.get(ipAddressList.size() - 1);
        assertThat(testIpAddress.getIpAdress()).isEqualTo(DEFAULT_IP_ADRESS);
    }

    @Test
    @Transactional
    public void createIpAddressWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = ipAddressRepository.findAll().size();

        // Create the IpAddress with an existing ID
        ipAddress.setId(1L);
        IpAddressDTO ipAddressDTO = ipAddressMapper.toDto(ipAddress);

        // An entity with an existing ID cannot be created, so this API call must fail
        restIpAddressMockMvc.perform(post("/api/ip-addresses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ipAddressDTO)))
            .andExpect(status().isBadRequest());

        // Validate the IpAddress in the database
        List<IpAddress> ipAddressList = ipAddressRepository.findAll();
        assertThat(ipAddressList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllIpAddresses() throws Exception {
        // Initialize the database
        ipAddressRepository.saveAndFlush(ipAddress);

        // Get all the ipAddressList
        restIpAddressMockMvc.perform(get("/api/ip-addresses?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(ipAddress.getId().intValue())))
            .andExpect(jsonPath("$.[*].ipAdress").value(hasItem(DEFAULT_IP_ADRESS)));
    }
    
    @Test
    @Transactional
    public void getIpAddress() throws Exception {
        // Initialize the database
        ipAddressRepository.saveAndFlush(ipAddress);

        // Get the ipAddress
        restIpAddressMockMvc.perform(get("/api/ip-addresses/{id}", ipAddress.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(ipAddress.getId().intValue()))
            .andExpect(jsonPath("$.ipAdress").value(DEFAULT_IP_ADRESS));
    }
    @Test
    @Transactional
    public void getNonExistingIpAddress() throws Exception {
        // Get the ipAddress
        restIpAddressMockMvc.perform(get("/api/ip-addresses/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateIpAddress() throws Exception {
        // Initialize the database
        ipAddressRepository.saveAndFlush(ipAddress);

        int databaseSizeBeforeUpdate = ipAddressRepository.findAll().size();

        // Update the ipAddress
        IpAddress updatedIpAddress = ipAddressRepository.findById(ipAddress.getId()).get();
        // Disconnect from session so that the updates on updatedIpAddress are not directly saved in db
        em.detach(updatedIpAddress);
        updatedIpAddress
            .ipAdress(UPDATED_IP_ADRESS);
        IpAddressDTO ipAddressDTO = ipAddressMapper.toDto(updatedIpAddress);

        restIpAddressMockMvc.perform(put("/api/ip-addresses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ipAddressDTO)))
            .andExpect(status().isOk());

        // Validate the IpAddress in the database
        List<IpAddress> ipAddressList = ipAddressRepository.findAll();
        assertThat(ipAddressList).hasSize(databaseSizeBeforeUpdate);
        IpAddress testIpAddress = ipAddressList.get(ipAddressList.size() - 1);
        assertThat(testIpAddress.getIpAdress()).isEqualTo(UPDATED_IP_ADRESS);
    }

    @Test
    @Transactional
    public void updateNonExistingIpAddress() throws Exception {
        int databaseSizeBeforeUpdate = ipAddressRepository.findAll().size();

        // Create the IpAddress
        IpAddressDTO ipAddressDTO = ipAddressMapper.toDto(ipAddress);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restIpAddressMockMvc.perform(put("/api/ip-addresses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ipAddressDTO)))
            .andExpect(status().isBadRequest());

        // Validate the IpAddress in the database
        List<IpAddress> ipAddressList = ipAddressRepository.findAll();
        assertThat(ipAddressList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteIpAddress() throws Exception {
        // Initialize the database
        ipAddressRepository.saveAndFlush(ipAddress);

        int databaseSizeBeforeDelete = ipAddressRepository.findAll().size();

        // Delete the ipAddress
        restIpAddressMockMvc.perform(delete("/api/ip-addresses/{id}", ipAddress.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<IpAddress> ipAddressList = ipAddressRepository.findAll();
        assertThat(ipAddressList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
