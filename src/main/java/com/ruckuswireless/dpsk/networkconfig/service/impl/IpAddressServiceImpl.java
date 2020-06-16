package com.ruckuswireless.dpsk.networkconfig.service.impl;

import com.ruckuswireless.dpsk.networkconfig.service.IpAddressService;
import com.ruckuswireless.dpsk.networkconfig.domain.IpAddress;
import com.ruckuswireless.dpsk.networkconfig.repository.IpAddressRepository;
import com.ruckuswireless.dpsk.networkconfig.service.dto.IpAddressDTO;
import com.ruckuswireless.dpsk.networkconfig.service.mapper.IpAddressMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link IpAddress}.
 */
@Service
@Transactional
public class IpAddressServiceImpl implements IpAddressService {

    private final Logger log = LoggerFactory.getLogger(IpAddressServiceImpl.class);

    private final IpAddressRepository ipAddressRepository;

    private final IpAddressMapper ipAddressMapper;

    public IpAddressServiceImpl(IpAddressRepository ipAddressRepository, IpAddressMapper ipAddressMapper) {
        this.ipAddressRepository = ipAddressRepository;
        this.ipAddressMapper = ipAddressMapper;
    }

    /**
     * Save a ipAddress.
     *
     * @param ipAddressDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public IpAddressDTO save(IpAddressDTO ipAddressDTO) {
        log.debug("Request to save IpAddress : {}", ipAddressDTO);
        IpAddress ipAddress = ipAddressMapper.toEntity(ipAddressDTO);
        ipAddress = ipAddressRepository.save(ipAddress);
        return ipAddressMapper.toDto(ipAddress);
    }

    /**
     * Get all the ipAddresses.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<IpAddressDTO> findAll() {
        log.debug("Request to get all IpAddresses");
        return ipAddressRepository.findAll().stream()
            .map(ipAddressMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one ipAddress by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<IpAddressDTO> findOne(Long id) {
        log.debug("Request to get IpAddress : {}", id);
        return ipAddressRepository.findById(id)
            .map(ipAddressMapper::toDto);
    }

    /**
     * Delete the ipAddress by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete IpAddress : {}", id);
        ipAddressRepository.deleteById(id);
    }
}
