package com.ruckuswireless.dpsk.networkconfig.web.rest;

import com.ruckuswireless.dpsk.networkconfig.service.AuthorizationServerService;
import com.ruckuswireless.dpsk.networkconfig.web.rest.errors.BadRequestAlertException;
import com.ruckuswireless.dpsk.networkconfig.service.dto.AuthorizationServerDTO;

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
 * REST controller for managing {@link com.ruckuswireless.dpsk.networkconfig.domain.AuthorizationServer}.
 */
@RestController
@RequestMapping("/api")
public class AuthorizationServerResource {

    private final Logger log = LoggerFactory.getLogger(AuthorizationServerResource.class);

    private static final String ENTITY_NAME = "altoDpsk1AuthorizationServer";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AuthorizationServerService authorizationServerService;

    public AuthorizationServerResource(AuthorizationServerService authorizationServerService) {
        this.authorizationServerService = authorizationServerService;
    }

    /**
     * {@code POST  /authorization-servers} : Create a new authorizationServer.
     *
     * @param authorizationServerDTO the authorizationServerDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new authorizationServerDTO, or with status {@code 400 (Bad Request)} if the authorizationServer has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/authorization-servers")
    public ResponseEntity<AuthorizationServerDTO> createAuthorizationServer(@RequestBody AuthorizationServerDTO authorizationServerDTO) throws URISyntaxException {
        log.debug("REST request to save AuthorizationServer : {}", authorizationServerDTO);
        if (authorizationServerDTO.getId() != null) {
            throw new BadRequestAlertException("A new authorizationServer cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AuthorizationServerDTO result = authorizationServerService.save(authorizationServerDTO);
        return ResponseEntity.created(new URI("/api/authorization-servers/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /authorization-servers} : Updates an existing authorizationServer.
     *
     * @param authorizationServerDTO the authorizationServerDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated authorizationServerDTO,
     * or with status {@code 400 (Bad Request)} if the authorizationServerDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the authorizationServerDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/authorization-servers")
    public ResponseEntity<AuthorizationServerDTO> updateAuthorizationServer(@RequestBody AuthorizationServerDTO authorizationServerDTO) throws URISyntaxException {
        log.debug("REST request to update AuthorizationServer : {}", authorizationServerDTO);
        if (authorizationServerDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AuthorizationServerDTO result = authorizationServerService.save(authorizationServerDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, authorizationServerDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /authorization-servers} : get all the authorizationServers.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of authorizationServers in body.
     */
    @GetMapping("/authorization-servers")
    public List<AuthorizationServerDTO> getAllAuthorizationServers() {
        log.debug("REST request to get all AuthorizationServers");
        return authorizationServerService.findAll();
    }

    /**
     * {@code GET  /authorization-servers/:id} : get the "id" authorizationServer.
     *
     * @param id the id of the authorizationServerDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the authorizationServerDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/authorization-servers/{id}")
    public ResponseEntity<AuthorizationServerDTO> getAuthorizationServer(@PathVariable Long id) {
        log.debug("REST request to get AuthorizationServer : {}", id);
        Optional<AuthorizationServerDTO> authorizationServerDTO = authorizationServerService.findOne(id);
        return ResponseUtil.wrapOrNotFound(authorizationServerDTO);
    }

    /**
     * {@code DELETE  /authorization-servers/:id} : delete the "id" authorizationServer.
     *
     * @param id the id of the authorizationServerDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/authorization-servers/{id}")
    public ResponseEntity<Void> deleteAuthorizationServer(@PathVariable Long id) {
        log.debug("REST request to delete AuthorizationServer : {}", id);
        authorizationServerService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
