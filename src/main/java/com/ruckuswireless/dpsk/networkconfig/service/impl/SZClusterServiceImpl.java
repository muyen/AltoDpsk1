package com.ruckuswireless.dpsk.networkconfig.service.impl;

import com.ruckuswireless.dpsk.networkconfig.service.SZClusterService;
import com.ruckuswireless.dpsk.networkconfig.domain.SZCluster;
import com.ruckuswireless.dpsk.networkconfig.repository.SZClusterRepository;
import com.ruckuswireless.dpsk.networkconfig.service.dto.SZClusterDTO;
import com.ruckuswireless.dpsk.networkconfig.service.mapper.SZClusterMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link SZCluster}.
 */
@Service
@Transactional
public class SZClusterServiceImpl implements SZClusterService {

    private final Logger log = LoggerFactory.getLogger(SZClusterServiceImpl.class);

    private final SZClusterRepository sZClusterRepository;

    private final SZClusterMapper sZClusterMapper;

    public SZClusterServiceImpl(SZClusterRepository sZClusterRepository, SZClusterMapper sZClusterMapper) {
        this.sZClusterRepository = sZClusterRepository;
        this.sZClusterMapper = sZClusterMapper;
    }

    /**
     * Save a sZCluster.
     *
     * @param sZClusterDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public SZClusterDTO save(SZClusterDTO sZClusterDTO) {
        log.debug("Request to save SZCluster : {}", sZClusterDTO);
        SZCluster sZCluster = sZClusterMapper.toEntity(sZClusterDTO);
        sZCluster = sZClusterRepository.save(sZCluster);
        return sZClusterMapper.toDto(sZCluster);
    }

    /**
     * Get all the sZClusters.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<SZClusterDTO> findAll() {
        log.debug("Request to get all SZClusters");
        return sZClusterRepository.findAll().stream()
            .map(sZClusterMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one sZCluster by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<SZClusterDTO> findOne(Long id) {
        log.debug("Request to get SZCluster : {}", id);
        return sZClusterRepository.findById(id)
            .map(sZClusterMapper::toDto);
    }

    /**
     * Delete the sZCluster by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete SZCluster : {}", id);
        sZClusterRepository.deleteById(id);
    }
}
