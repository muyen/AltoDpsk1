package com.ruckuswireless.dpsk.networkconfig.service;

import com.ruckuswireless.dpsk.networkconfig.service.dto.IpAddressDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.ruckuswireless.dpsk.networkconfig.domain.IpAddress}.
 */
public interface IpAddressService {

    /**
     * Save a ipAddress.
     *
     * @param ipAddressDTO the entity to save.
     * @return the persisted entity.
     */
    IpAddressDTO save(IpAddressDTO ipAddressDTO);

    /**
     * Get all the ipAddresses.
     *
     * @return the list of entities.
     */
    List<IpAddressDTO> findAll();


    /**
     * Get the "id" ipAddress.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<IpAddressDTO> findOne(Long id);

    /**
     * Delete the "id" ipAddress.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
