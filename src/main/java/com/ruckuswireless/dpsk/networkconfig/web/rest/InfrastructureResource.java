package com.ruckuswireless.dpsk.networkconfig.web.rest;

import com.ruckuswireless.dpsk.networkconfig.service.InfrastructureService;
import com.ruckuswireless.dpsk.networkconfig.web.rest.errors.BadRequestAlertException;
import com.ruckuswireless.dpsk.networkconfig.service.dto.InfrastructureDTO;

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
 * REST controller for managing {@link com.ruckuswireless.dpsk.networkconfig.domain.Infrastructure}.
 */
@RestController
@RequestMapping("/api")
public class InfrastructureResource {

    private final Logger log = LoggerFactory.getLogger(InfrastructureResource.class);

    private static final String ENTITY_NAME = "altoDpsk1Infrastructure";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final InfrastructureService infrastructureService;

    public InfrastructureResource(InfrastructureService infrastructureService) {
        this.infrastructureService = infrastructureService;
    }

    /**
     * {@code POST  /infrastructures} : Create a new infrastructure.
     *
     * @param infrastructureDTO the infrastructureDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new infrastructureDTO, or with status {@code 400 (Bad Request)} if the infrastructure has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/infrastructures")
    public ResponseEntity<InfrastructureDTO> createInfrastructure(@RequestBody InfrastructureDTO infrastructureDTO) throws URISyntaxException {
        log.debug("REST request to save Infrastructure : {}", infrastructureDTO);
        if (infrastructureDTO.getId() != null) {
            throw new BadRequestAlertException("A new infrastructure cannot already have an ID", ENTITY_NAME, "idexists");
        }
        InfrastructureDTO result = infrastructureService.save(infrastructureDTO);
        return ResponseEntity.created(new URI("/api/infrastructures/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /infrastructures} : Updates an existing infrastructure.
     *
     * @param infrastructureDTO the infrastructureDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated infrastructureDTO,
     * or with status {@code 400 (Bad Request)} if the infrastructureDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the infrastructureDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/infrastructures")
    public ResponseEntity<InfrastructureDTO> updateInfrastructure(@RequestBody InfrastructureDTO infrastructureDTO) throws URISyntaxException {
        log.debug("REST request to update Infrastructure : {}", infrastructureDTO);
        if (infrastructureDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        InfrastructureDTO result = infrastructureService.save(infrastructureDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, infrastructureDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /infrastructures} : get all the infrastructures.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of infrastructures in body.
     */
    @GetMapping("/infrastructures")
    public List<InfrastructureDTO> getAllInfrastructures() {
        log.debug("REST request to get all Infrastructures");
        return infrastructureService.findAll();
    }

    /**
     * {@code GET  /infrastructures/:id} : get the "id" infrastructure.
     *
     * @param id the id of the infrastructureDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the infrastructureDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/infrastructures/{id}")
    public ResponseEntity<InfrastructureDTO> getInfrastructure(@PathVariable Long id) {
        log.debug("REST request to get Infrastructure : {}", id);
        Optional<InfrastructureDTO> infrastructureDTO = infrastructureService.findOne(id);
        return ResponseUtil.wrapOrNotFound(infrastructureDTO);
    }

    /**
     * {@code DELETE  /infrastructures/:id} : delete the "id" infrastructure.
     *
     * @param id the id of the infrastructureDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/infrastructures/{id}")
    public ResponseEntity<Void> deleteInfrastructure(@PathVariable Long id) {
        log.debug("REST request to delete Infrastructure : {}", id);
        infrastructureService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
