package com.ruckuswireless.dpsk.networkconfig.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.ruckuswireless.dpsk.networkconfig.web.rest.TestUtil;

public class AuthorizationServerDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AuthorizationServerDTO.class);
        AuthorizationServerDTO authorizationServerDTO1 = new AuthorizationServerDTO();
        authorizationServerDTO1.setId(1L);
        AuthorizationServerDTO authorizationServerDTO2 = new AuthorizationServerDTO();
        assertThat(authorizationServerDTO1).isNotEqualTo(authorizationServerDTO2);
        authorizationServerDTO2.setId(authorizationServerDTO1.getId());
        assertThat(authorizationServerDTO1).isEqualTo(authorizationServerDTO2);
        authorizationServerDTO2.setId(2L);
        assertThat(authorizationServerDTO1).isNotEqualTo(authorizationServerDTO2);
        authorizationServerDTO1.setId(null);
        assertThat(authorizationServerDTO1).isNotEqualTo(authorizationServerDTO2);
    }
}
