package com.ruckuswireless.dpsk.networkconfig.service;

import com.ruckuswireless.dpsk.networkconfig.service.dto.AuthorizationServerDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.ruckuswireless.dpsk.networkconfig.domain.AuthorizationServer}.
 */
public interface AuthorizationServerService {

    /**
     * Save a authorizationServer.
     *
     * @param authorizationServerDTO the entity to save.
     * @return the persisted entity.
     */
    AuthorizationServerDTO save(AuthorizationServerDTO authorizationServerDTO);

    /**
     * Get all the authorizationServers.
     *
     * @return the list of entities.
     */
    List<AuthorizationServerDTO> findAll();


    /**
     * Get the "id" authorizationServer.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<AuthorizationServerDTO> findOne(Long id);

    /**
     * Delete the "id" authorizationServer.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
