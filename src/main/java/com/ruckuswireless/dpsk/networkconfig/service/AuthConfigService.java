package com.ruckuswireless.dpsk.networkconfig.service;

import com.ruckuswireless.dpsk.networkconfig.service.dto.AuthConfigDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.ruckuswireless.dpsk.networkconfig.domain.AuthConfig}.
 */
public interface AuthConfigService {

    /**
     * Save a authConfig.
     *
     * @param authConfigDTO the entity to save.
     * @return the persisted entity.
     */
    AuthConfigDTO save(AuthConfigDTO authConfigDTO);

    /**
     * Get all the authConfigs.
     *
     * @return the list of entities.
     */
    List<AuthConfigDTO> findAll();


    /**
     * Get the "id" authConfig.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<AuthConfigDTO> findOne(Long id);

    /**
     * Delete the "id" authConfig.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
