package com.ruckuswireless.dpsk.networkconfig.repository;

import com.ruckuswireless.dpsk.networkconfig.domain.AuthorizationServer;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the AuthorizationServer entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AuthorizationServerRepository extends JpaRepository<AuthorizationServer, Long> {
}
