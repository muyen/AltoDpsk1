package com.ruckuswireless.dpsk.networkconfig.service.impl;

import com.ruckuswireless.dpsk.networkconfig.service.AuthConfigService;
import com.ruckuswireless.dpsk.networkconfig.domain.AuthConfig;
import com.ruckuswireless.dpsk.networkconfig.repository.AuthConfigRepository;
import com.ruckuswireless.dpsk.networkconfig.service.dto.AuthConfigDTO;
import com.ruckuswireless.dpsk.networkconfig.service.mapper.AuthConfigMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link AuthConfig}.
 */
@Service
@Transactional
public class AuthConfigServiceImpl implements AuthConfigService {

    private final Logger log = LoggerFactory.getLogger(AuthConfigServiceImpl.class);

    private final AuthConfigRepository authConfigRepository;

    private final AuthConfigMapper authConfigMapper;

    public AuthConfigServiceImpl(AuthConfigRepository authConfigRepository, AuthConfigMapper authConfigMapper) {
        this.authConfigRepository = authConfigRepository;
        this.authConfigMapper = authConfigMapper;
    }

    /**
     * Save a authConfig.
     *
     * @param authConfigDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public AuthConfigDTO save(AuthConfigDTO authConfigDTO) {
        log.debug("Request to save AuthConfig : {}", authConfigDTO);
        AuthConfig authConfig = authConfigMapper.toEntity(authConfigDTO);
        authConfig = authConfigRepository.save(authConfig);
        return authConfigMapper.toDto(authConfig);
    }

    /**
     * Get all the authConfigs.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<AuthConfigDTO> findAll() {
        log.debug("Request to get all AuthConfigs");
        return authConfigRepository.findAll().stream()
            .map(authConfigMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one authConfig by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<AuthConfigDTO> findOne(Long id) {
        log.debug("Request to get AuthConfig : {}", id);
        return authConfigRepository.findById(id)
            .map(authConfigMapper::toDto);
    }

    /**
     * Delete the authConfig by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete AuthConfig : {}", id);
        authConfigRepository.deleteById(id);
    }
}
