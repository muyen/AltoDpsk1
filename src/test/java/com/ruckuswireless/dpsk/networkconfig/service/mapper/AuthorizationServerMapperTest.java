package com.ruckuswireless.dpsk.networkconfig.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class AuthorizationServerMapperTest {

    private AuthorizationServerMapper authorizationServerMapper;

    @BeforeEach
    public void setUp() {
        authorizationServerMapper = new AuthorizationServerMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(authorizationServerMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(authorizationServerMapper.fromId(null)).isNull();
    }
}
