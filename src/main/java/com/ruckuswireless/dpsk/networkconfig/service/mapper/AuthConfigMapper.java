package com.ruckuswireless.dpsk.networkconfig.service.mapper;


import com.ruckuswireless.dpsk.networkconfig.domain.*;
import com.ruckuswireless.dpsk.networkconfig.service.dto.AuthConfigDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link AuthConfig} and its DTO {@link AuthConfigDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface AuthConfigMapper extends EntityMapper<AuthConfigDTO, AuthConfig> {



    default AuthConfig fromId(Long id) {
        if (id == null) {
            return null;
        }
        AuthConfig authConfig = new AuthConfig();
        authConfig.setId(id);
        return authConfig;
    }
}
