package com.ruckuswireless.dpsk.networkconfig.service;

import com.ruckuswireless.dpsk.networkconfig.service.dto.SZClusterDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.ruckuswireless.dpsk.networkconfig.domain.SZCluster}.
 */
public interface SZClusterService {

    /**
     * Save a sZCluster.
     *
     * @param sZClusterDTO the entity to save.
     * @return the persisted entity.
     */
    SZClusterDTO save(SZClusterDTO sZClusterDTO);

    /**
     * Get all the sZClusters.
     *
     * @return the list of entities.
     */
    List<SZClusterDTO> findAll();


    /**
     * Get the "id" sZCluster.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<SZClusterDTO> findOne(Long id);

    /**
     * Delete the "id" sZCluster.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
