package com.ruckuswireless.dpsk.networkconfig.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.ruckuswireless.dpsk.networkconfig.web.rest.TestUtil;

public class AuthorizationServerTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AuthorizationServer.class);
        AuthorizationServer authorizationServer1 = new AuthorizationServer();
        authorizationServer1.setId(1L);
        AuthorizationServer authorizationServer2 = new AuthorizationServer();
        authorizationServer2.setId(authorizationServer1.getId());
        assertThat(authorizationServer1).isEqualTo(authorizationServer2);
        authorizationServer2.setId(2L);
        assertThat(authorizationServer1).isNotEqualTo(authorizationServer2);
        authorizationServer1.setId(null);
        assertThat(authorizationServer1).isNotEqualTo(authorizationServer2);
    }
}
