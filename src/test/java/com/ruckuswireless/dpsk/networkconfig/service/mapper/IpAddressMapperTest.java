package com.ruckuswireless.dpsk.networkconfig.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class IpAddressMapperTest {

    private IpAddressMapper ipAddressMapper;

    @BeforeEach
    public void setUp() {
        ipAddressMapper = new IpAddressMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(ipAddressMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(ipAddressMapper.fromId(null)).isNull();
    }
}
