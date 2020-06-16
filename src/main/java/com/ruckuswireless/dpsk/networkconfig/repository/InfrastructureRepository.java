package com.ruckuswireless.dpsk.networkconfig.repository;

import com.ruckuswireless.dpsk.networkconfig.domain.Infrastructure;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Infrastructure entity.
 */
@SuppressWarnings("unused")
@Repository
public interface InfrastructureRepository extends JpaRepository<Infrastructure, Long> {
}
