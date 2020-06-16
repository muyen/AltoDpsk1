package com.ruckuswireless.dpsk.networkconfig.web.rest;

import com.ruckuswireless.dpsk.networkconfig.service.IpAddressService;
import com.ruckuswireless.dpsk.networkconfig.web.rest.errors.BadRequestAlertException;
import com.ruckuswireless.dpsk.networkconfig.service.dto.IpAddressDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.ruckuswireless.dpsk.networkconfig.domain.IpAddress}.
 */
@RestController
@RequestMapping("/api")
public class IpAddressResource {

    private final Logger log = LoggerFactory.getLogger(IpAddressResource.class);

    private static final String ENTITY_NAME = "altoDpsk1IpAddress";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final IpAddressService ipAddressService;

    public IpAddressResource(IpAddressService ipAddressService) {
        this.ipAddressService = ipAddressService;
    }

    /**
     * {@code POST  /ip-addresses} : Create a new ipAddress.
     *
     * @param ipAddressDTO the ipAddressDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new ipAddressDTO, or with status {@code 400 (Bad Request)} if the ipAddress has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/ip-addresses")
    public ResponseEntity<IpAddressDTO> createIpAddress(@RequestBody IpAddressDTO ipAddressDTO) throws URISyntaxException {
        log.debug("REST request to save IpAddress : {}", ipAddressDTO);
        if (ipAddressDTO.getId() != null) {
            throw new BadRequestAlertException("A new ipAddress cannot already have an ID", ENTITY_NAME, "idexists");
        }
        IpAddressDTO result = ipAddressService.save(ipAddressDTO);
        return ResponseEntity.created(new URI("/api/ip-addresses/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /ip-addresses} : Updates an existing ipAddress.
     *
     * @param ipAddressDTO the ipAddressDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated ipAddressDTO,
     * or with status {@code 400 (Bad Request)} if the ipAddressDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the ipAddressDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/ip-addresses")
    public ResponseEntity<IpAddressDTO> updateIpAddress(@RequestBody IpAddressDTO ipAddressDTO) throws URISyntaxException {
        log.debug("REST request to update IpAddress : {}", ipAddressDTO);
        if (ipAddressDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        IpAddressDTO result = ipAddressService.save(ipAddressDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, ipAddressDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /ip-addresses} : get all the ipAddresses.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of ipAddresses in body.
     */
    @GetMapping("/ip-addresses")
    public List<IpAddressDTO> getAllIpAddresses() {
        log.debug("REST request to get all IpAddresses");
        return ipAddressService.findAll();
    }

    /**
     * {@code GET  /ip-addresses/:id} : get the "id" ipAddress.
     *
     * @param id the id of the ipAddressDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the ipAddressDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/ip-addresses/{id}")
    public ResponseEntity<IpAddressDTO> getIpAddress(@PathVariable Long id) {
        log.debug("REST request to get IpAddress : {}", id);
        Optional<IpAddressDTO> ipAddressDTO = ipAddressService.findOne(id);
        return ResponseUtil.wrapOrNotFound(ipAddressDTO);
    }

    /**
     * {@code DELETE  /ip-addresses/:id} : delete the "id" ipAddress.
     *
     * @param id the id of the ipAddressDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/ip-addresses/{id}")
    public ResponseEntity<Void> deleteIpAddress(@PathVariable Long id) {
        log.debug("REST request to delete IpAddress : {}", id);
        ipAddressService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
