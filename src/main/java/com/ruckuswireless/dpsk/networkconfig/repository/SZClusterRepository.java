package com.ruckuswireless.dpsk.networkconfig.repository;

import com.ruckuswireless.dpsk.networkconfig.domain.SZCluster;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the SZCluster entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SZClusterRepository extends JpaRepository<SZCluster, Long> {
}
