package com.ruckuswireless.dpsk.networkconfig.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class InfrastructureMapperTest {

    private InfrastructureMapper infrastructureMapper;

    @BeforeEach
    public void setUp() {
        infrastructureMapper = new InfrastructureMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(infrastructureMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(infrastructureMapper.fromId(null)).isNull();
    }
}
