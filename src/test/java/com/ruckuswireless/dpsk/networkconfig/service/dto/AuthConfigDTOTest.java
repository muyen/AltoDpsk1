package com.ruckuswireless.dpsk.networkconfig.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.ruckuswireless.dpsk.networkconfig.web.rest.TestUtil;

public class AuthConfigDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AuthConfigDTO.class);
        AuthConfigDTO authConfigDTO1 = new AuthConfigDTO();
        authConfigDTO1.setId(1L);
        AuthConfigDTO authConfigDTO2 = new AuthConfigDTO();
        assertThat(authConfigDTO1).isNotEqualTo(authConfigDTO2);
        authConfigDTO2.setId(authConfigDTO1.getId());
        assertThat(authConfigDTO1).isEqualTo(authConfigDTO2);
        authConfigDTO2.setId(2L);
        assertThat(authConfigDTO1).isNotEqualTo(authConfigDTO2);
        authConfigDTO1.setId(null);
        assertThat(authConfigDTO1).isNotEqualTo(authConfigDTO2);
    }
}
