package com.ruckuswireless.dpsk.networkconfig.service.mapper;


import com.ruckuswireless.dpsk.networkconfig.domain.*;
import com.ruckuswireless.dpsk.networkconfig.service.dto.IpAddressDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link IpAddress} and its DTO {@link IpAddressDTO}.
 */
@Mapper(componentModel = "spring", uses = {SZClusterMapper.class})
public interface IpAddressMapper extends EntityMapper<IpAddressDTO, IpAddress> {

    @Mapping(source = "szCluster.id", target = "szClusterId")
    IpAddressDTO toDto(IpAddress ipAddress);

    @Mapping(source = "szClusterId", target = "szCluster")
    IpAddress toEntity(IpAddressDTO ipAddressDTO);

    default IpAddress fromId(Long id) {
        if (id == null) {
            return null;
        }
        IpAddress ipAddress = new IpAddress();
        ipAddress.setId(id);
        return ipAddress;
    }
}
