package com.ruckuswireless.dpsk.networkconfig.service.mapper;


import com.ruckuswireless.dpsk.networkconfig.domain.*;
import com.ruckuswireless.dpsk.networkconfig.service.dto.InfrastructureDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Infrastructure} and its DTO {@link InfrastructureDTO}.
 */
@Mapper(componentModel = "spring", uses = {AuthorizationServerMapper.class})
public interface InfrastructureMapper extends EntityMapper<InfrastructureDTO, Infrastructure> {

    @Mapping(source = "authorizationServer.id", target = "authorizationServerId")
    InfrastructureDTO toDto(Infrastructure infrastructure);

    @Mapping(source = "authorizationServerId", target = "authorizationServer")
    @Mapping(target = "sZClusters", ignore = true)
    @Mapping(target = "removeSZCluster", ignore = true)
    Infrastructure toEntity(InfrastructureDTO infrastructureDTO);

    default Infrastructure fromId(Long id) {
        if (id == null) {
            return null;
        }
        Infrastructure infrastructure = new Infrastructure();
        infrastructure.setId(id);
        return infrastructure;
    }
}
