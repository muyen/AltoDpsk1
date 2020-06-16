package com.ruckuswireless.dpsk.networkconfig.service;

import com.ruckuswireless.dpsk.networkconfig.service.dto.InfrastructureDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.ruckuswireless.dpsk.networkconfig.domain.Infrastructure}.
 */
public interface InfrastructureService {

    /**
     * Save a infrastructure.
     *
     * @param infrastructureDTO the entity to save.
     * @return the persisted entity.
     */
    InfrastructureDTO save(InfrastructureDTO infrastructureDTO);

    /**
     * Get all the infrastructures.
     *
     * @return the list of entities.
     */
    List<InfrastructureDTO> findAll();


    /**
     * Get the "id" infrastructure.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<InfrastructureDTO> findOne(Long id);

    /**
     * Delete the "id" infrastructure.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
