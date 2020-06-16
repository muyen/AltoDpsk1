package com.ruckuswireless.dpsk.networkconfig.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class SZClusterMapperTest {

    private SZClusterMapper sZClusterMapper;

    @BeforeEach
    public void setUp() {
        sZClusterMapper = new SZClusterMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(sZClusterMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(sZClusterMapper.fromId(null)).isNull();
    }
}
