package com.ruckuswireless.dpsk.networkconfig.web.rest;

import com.ruckuswireless.dpsk.networkconfig.service.AuthConfigService;
import com.ruckuswireless.dpsk.networkconfig.web.rest.errors.BadRequestAlertException;
import com.ruckuswireless.dpsk.networkconfig.service.dto.AuthConfigDTO;

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
 * REST controller for managing {@link com.ruckuswireless.dpsk.networkconfig.domain.AuthConfig}.
 */
@RestController
@RequestMapping("/api")
public class AuthConfigResource {

    private final Logger log = LoggerFactory.getLogger(AuthConfigResource.class);

    private static final String ENTITY_NAME = "altoDpsk1AuthConfig";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AuthConfigService authConfigService;

    public AuthConfigResource(AuthConfigService authConfigService) {
        this.authConfigService = authConfigService;
    }

    /**
     * {@code POST  /auth-configs} : Create a new authConfig.
     *
     * @param authConfigDTO the authConfigDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new authConfigDTO, or with status {@code 400 (Bad Request)} if the authConfig has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/auth-configs")
    public ResponseEntity<AuthConfigDTO> createAuthConfig(@RequestBody AuthConfigDTO authConfigDTO) throws URISyntaxException {
        log.debug("REST request to save AuthConfig : {}", authConfigDTO);
        if (authConfigDTO.getId() != null) {
            throw new BadRequestAlertException("A new authConfig cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AuthConfigDTO result = authConfigService.save(authConfigDTO);
        return ResponseEntity.created(new URI("/api/auth-configs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /auth-configs} : Updates an existing authConfig.
     *
     * @param authConfigDTO the authConfigDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated authConfigDTO,
     * or with status {@code 400 (Bad Request)} if the authConfigDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the authConfigDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/auth-configs")
    public ResponseEntity<AuthConfigDTO> updateAuthConfig(@RequestBody AuthConfigDTO authConfigDTO) throws URISyntaxException {
        log.debug("REST request to update AuthConfig : {}", authConfigDTO);
        if (authConfigDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AuthConfigDTO result = authConfigService.save(authConfigDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, authConfigDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /auth-configs} : get all the authConfigs.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of authConfigs in body.
     */
    @GetMapping("/auth-configs")
    public List<AuthConfigDTO> getAllAuthConfigs() {
        log.debug("REST request to get all AuthConfigs");
        return authConfigService.findAll();
    }

    /**
     * {@code GET  /auth-configs/:id} : get the "id" authConfig.
     *
     * @param id the id of the authConfigDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the authConfigDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/auth-configs/{id}")
    public ResponseEntity<AuthConfigDTO> getAuthConfig(@PathVariable Long id) {
        log.debug("REST request to get AuthConfig : {}", id);
        Optional<AuthConfigDTO> authConfigDTO = authConfigService.findOne(id);
        return ResponseUtil.wrapOrNotFound(authConfigDTO);
    }

    /**
     * {@code DELETE  /auth-configs/:id} : delete the "id" authConfig.
     *
     * @param id the id of the authConfigDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/auth-configs/{id}")
    public ResponseEntity<Void> deleteAuthConfig(@PathVariable Long id) {
        log.debug("REST request to delete AuthConfig : {}", id);
        authConfigService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
