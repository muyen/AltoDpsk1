package com.ruckuswireless.dpsk.networkconfig.repository;

import com.ruckuswireless.dpsk.networkconfig.domain.IpAddress;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the IpAddress entity.
 */
@SuppressWarnings("unused")
@Repository
public interface IpAddressRepository extends JpaRepository<IpAddress, Long> {
}
