package com.ruckuswireless.dpsk.networkconfig.service.impl;

import com.ruckuswireless.dpsk.networkconfig.service.InfrastructureService;
import com.ruckuswireless.dpsk.networkconfig.domain.Infrastructure;
import com.ruckuswireless.dpsk.networkconfig.repository.InfrastructureRepository;
import com.ruckuswireless.dpsk.networkconfig.service.dto.InfrastructureDTO;
import com.ruckuswireless.dpsk.networkconfig.service.mapper.InfrastructureMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Infrastructure}.
 */
@Service
@Transactional
public class InfrastructureServiceImpl implements InfrastructureService {

    private final Logger log = LoggerFactory.getLogger(InfrastructureServiceImpl.class);

    private final InfrastructureRepository infrastructureRepository;

    private final InfrastructureMapper infrastructureMapper;

    public InfrastructureServiceImpl(InfrastructureRepository infrastructureRepository, InfrastructureMapper infrastructureMapper) {
        this.infrastructureRepository = infrastructureRepository;
        this.infrastructureMapper = infrastructureMapper;
    }

    /**
     * Save a infrastructure.
     *
     * @param infrastructureDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public InfrastructureDTO save(InfrastructureDTO infrastructureDTO) {
        log.debug("Request to save Infrastructure : {}", infrastructureDTO);
        Infrastructure infrastructure = infrastructureMapper.toEntity(infrastructureDTO);
        infrastructure = infrastructureRepository.save(infrastructure);
        return infrastructureMapper.toDto(infrastructure);
    }

    /**
     * Get all the infrastructures.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<InfrastructureDTO> findAll() {
        log.debug("Request to get all Infrastructures");
        return infrastructureRepository.findAll().stream()
            .map(infrastructureMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one infrastructure by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<InfrastructureDTO> findOne(Long id) {
        log.debug("Request to get Infrastructure : {}", id);
        return infrastructureRepository.findById(id)
            .map(infrastructureMapper::toDto);
    }

    /**
     * Delete the infrastructure by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Infrastructure : {}", id);
        infrastructureRepository.deleteById(id);
    }
}
