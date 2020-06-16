package com.ruckuswireless.dpsk.networkconfig.service.mapper;


import com.ruckuswireless.dpsk.networkconfig.domain.*;
import com.ruckuswireless.dpsk.networkconfig.service.dto.SZClusterDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link SZCluster} and its DTO {@link SZClusterDTO}.
 */
@Mapper(componentModel = "spring", uses = {InfrastructureMapper.class})
public interface SZClusterMapper extends EntityMapper<SZClusterDTO, SZCluster> {

    @Mapping(source = "infrastructure.id", target = "infrastructureId")
    SZClusterDTO toDto(SZCluster sZCluster);

    @Mapping(target = "ipAddresses", ignore = true)
    @Mapping(target = "removeIpAddress", ignore = true)
    @Mapping(source = "infrastructureId", target = "infrastructure")
    SZCluster toEntity(SZClusterDTO sZClusterDTO);

    default SZCluster fromId(Long id) {
        if (id == null) {
            return null;
        }
        SZCluster sZCluster = new SZCluster();
        sZCluster.setId(id);
        return sZCluster;
    }
}
