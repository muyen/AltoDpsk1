package com.ruckuswireless.dpsk.networkconfig.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class AuthConfigMapperTest {

    private AuthConfigMapper authConfigMapper;

    @BeforeEach
    public void setUp() {
        authConfigMapper = new AuthConfigMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(authConfigMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(authConfigMapper.fromId(null)).isNull();
    }
}
