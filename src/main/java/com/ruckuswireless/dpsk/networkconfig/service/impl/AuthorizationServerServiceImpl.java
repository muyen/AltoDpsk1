package com.ruckuswireless.dpsk.networkconfig.service.impl;

import com.ruckuswireless.dpsk.networkconfig.service.AuthorizationServerService;
import com.ruckuswireless.dpsk.networkconfig.domain.AuthorizationServer;
import com.ruckuswireless.dpsk.networkconfig.repository.AuthorizationServerRepository;
import com.ruckuswireless.dpsk.networkconfig.service.dto.AuthorizationServerDTO;
import com.ruckuswireless.dpsk.networkconfig.service.mapper.AuthorizationServerMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link AuthorizationServer}.
 */
@Service
@Transactional
public class AuthorizationServerServiceImpl implements AuthorizationServerService {

    private final Logger log = LoggerFactory.getLogger(AuthorizationServerServiceImpl.class);

    private final AuthorizationServerRepository authorizationServerRepository;

    private final AuthorizationServerMapper authorizationServerMapper;

    public AuthorizationServerServiceImpl(AuthorizationServerRepository authorizationServerRepository, AuthorizationServerMapper authorizationServerMapper) {
        this.authorizationServerRepository = authorizationServerRepository;
        this.authorizationServerMapper = authorizationServerMapper;
    }

    /**
     * Save a authorizationServer.
     *
     * @param authorizationServerDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public AuthorizationServerDTO save(AuthorizationServerDTO authorizationServerDTO) {
        log.debug("Request to save AuthorizationServer : {}", authorizationServerDTO);
        AuthorizationServer authorizationServer = authorizationServerMapper.toEntity(authorizationServerDTO);
        authorizationServer = authorizationServerRepository.save(authorizationServer);
        return authorizationServerMapper.toDto(authorizationServer);
    }

    /**
     * Get all the authorizationServers.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<AuthorizationServerDTO> findAll() {
        log.debug("Request to get all AuthorizationServers");
        return authorizationServerRepository.findAll().stream()
            .map(authorizationServerMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one authorizationServer by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<AuthorizationServerDTO> findOne(Long id) {
        log.debug("Request to get AuthorizationServer : {}", id);
        return authorizationServerRepository.findById(id)
            .map(authorizationServerMapper::toDto);
    }

    /**
     * Delete the authorizationServer by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete AuthorizationServer : {}", id);
        authorizationServerRepository.deleteById(id);
    }
}
