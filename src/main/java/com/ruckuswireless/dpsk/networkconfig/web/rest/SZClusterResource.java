package com.ruckuswireless.dpsk.networkconfig.web.rest;

import com.ruckuswireless.dpsk.networkconfig.service.SZClusterService;
import com.ruckuswireless.dpsk.networkconfig.web.rest.errors.BadRequestAlertException;
import com.ruckuswireless.dpsk.networkconfig.service.dto.SZClusterDTO;

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
 * REST controller for managing {@link com.ruckuswireless.dpsk.networkconfig.domain.SZCluster}.
 */
@RestController
@RequestMapping("/api")
public class SZClusterResource {

    private final Logger log = LoggerFactory.getLogger(SZClusterResource.class);

    private static final String ENTITY_NAME = "altoDpsk1SzCluster";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SZClusterService sZClusterService;

    public SZClusterResource(SZClusterService sZClusterService) {
        this.sZClusterService = sZClusterService;
    }

    /**
     * {@code POST  /sz-clusters} : Create a new sZCluster.
     *
     * @param sZClusterDTO the sZClusterDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new sZClusterDTO, or with status {@code 400 (Bad Request)} if the sZCluster has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/sz-clusters")
    public ResponseEntity<SZClusterDTO> createSZCluster(@RequestBody SZClusterDTO sZClusterDTO) throws URISyntaxException {
        log.debug("REST request to save SZCluster : {}", sZClusterDTO);
        if (sZClusterDTO.getId() != null) {
            throw new BadRequestAlertException("A new sZCluster cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SZClusterDTO result = sZClusterService.save(sZClusterDTO);
        return ResponseEntity.created(new URI("/api/sz-clusters/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /sz-clusters} : Updates an existing sZCluster.
     *
     * @param sZClusterDTO the sZClusterDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated sZClusterDTO,
     * or with status {@code 400 (Bad Request)} if the sZClusterDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the sZClusterDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/sz-clusters")
    public ResponseEntity<SZClusterDTO> updateSZCluster(@RequestBody SZClusterDTO sZClusterDTO) throws URISyntaxException {
        log.debug("REST request to update SZCluster : {}", sZClusterDTO);
        if (sZClusterDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SZClusterDTO result = sZClusterService.save(sZClusterDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, sZClusterDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /sz-clusters} : get all the sZClusters.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of sZClusters in body.
     */
    @GetMapping("/sz-clusters")
    public List<SZClusterDTO> getAllSZClusters() {
        log.debug("REST request to get all SZClusters");
        return sZClusterService.findAll();
    }

    /**
     * {@code GET  /sz-clusters/:id} : get the "id" sZCluster.
     *
     * @param id the id of the sZClusterDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the sZClusterDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/sz-clusters/{id}")
    public ResponseEntity<SZClusterDTO> getSZCluster(@PathVariable Long id) {
        log.debug("REST request to get SZCluster : {}", id);
        Optional<SZClusterDTO> sZClusterDTO = sZClusterService.findOne(id);
        return ResponseUtil.wrapOrNotFound(sZClusterDTO);
    }

    /**
     * {@code DELETE  /sz-clusters/:id} : delete the "id" sZCluster.
     *
     * @param id the id of the sZClusterDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/sz-clusters/{id}")
    public ResponseEntity<Void> deleteSZCluster(@PathVariable Long id) {
        log.debug("REST request to delete SZCluster : {}", id);
        sZClusterService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
