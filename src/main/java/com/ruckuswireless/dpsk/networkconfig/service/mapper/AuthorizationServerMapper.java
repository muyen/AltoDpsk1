package com.ruckuswireless.dpsk.networkconfig.service.mapper;


import com.ruckuswireless.dpsk.networkconfig.domain.*;
import com.ruckuswireless.dpsk.networkconfig.service.dto.AuthorizationServerDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link AuthorizationServer} and its DTO {@link AuthorizationServerDTO}.
 */
@Mapper(componentModel = "spring", uses = {AuthConfigMapper.class})
public interface AuthorizationServerMapper extends EntityMapper<AuthorizationServerDTO, AuthorizationServer> {

    @Mapping(source = "primaryServer.id", target = "primaryServerId")
    @Mapping(source = "secondaryServer.id", target = "secondaryServerId")
    AuthorizationServerDTO toDto(AuthorizationServer authorizationServer);

    @Mapping(source = "primaryServerId", target = "primaryServer")
    @Mapping(source = "secondaryServerId", target = "secondaryServer")
    AuthorizationServer toEntity(AuthorizationServerDTO authorizationServerDTO);

    default AuthorizationServer fromId(Long id) {
        if (id == null) {
            return null;
        }
        AuthorizationServer authorizationServer = new AuthorizationServer();
        authorizationServer.setId(id);
        return authorizationServer;
    }
}
