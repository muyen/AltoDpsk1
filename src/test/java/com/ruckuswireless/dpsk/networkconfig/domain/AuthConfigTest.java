package com.ruckuswireless.dpsk.networkconfig.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.ruckuswireless.dpsk.networkconfig.web.rest.TestUtil;

public class AuthConfigTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AuthConfig.class);
        AuthConfig authConfig1 = new AuthConfig();
        authConfig1.setId(1L);
        AuthConfig authConfig2 = new AuthConfig();
        authConfig2.setId(authConfig1.getId());
        assertThat(authConfig1).isEqualTo(authConfig2);
        authConfig2.setId(2L);
        assertThat(authConfig1).isNotEqualTo(authConfig2);
        authConfig1.setId(null);
        assertThat(authConfig1).isNotEqualTo(authConfig2);
    }
}
