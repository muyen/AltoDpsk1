package com.ruckuswireless.dpsk.networkconfig.repository;

import com.ruckuswireless.dpsk.networkconfig.domain.AuthConfig;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the AuthConfig entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AuthConfigRepository extends JpaRepository<AuthConfig, Long> {
}
