package com.ruckuswireless.dpsk.networkconfig.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.ruckuswireless.dpsk.networkconfig.web.rest.TestUtil;

public class InfrastructureDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(InfrastructureDTO.class);
        InfrastructureDTO infrastructureDTO1 = new InfrastructureDTO();
        infrastructureDTO1.setId(1L);
        InfrastructureDTO infrastructureDTO2 = new InfrastructureDTO();
        assertThat(infrastructureDTO1).isNotEqualTo(infrastructureDTO2);
        infrastructureDTO2.setId(infrastructureDTO1.getId());
        assertThat(infrastructureDTO1).isEqualTo(infrastructureDTO2);
        infrastructureDTO2.setId(2L);
        assertThat(infrastructureDTO1).isNotEqualTo(infrastructureDTO2);
        infrastructureDTO1.setId(null);
        assertThat(infrastructureDTO1).isNotEqualTo(infrastructureDTO2);
    }
}
