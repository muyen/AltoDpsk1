package com.ruckuswireless.dpsk.networkconfig.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.ruckuswireless.dpsk.networkconfig.web.rest.TestUtil;

public class SZClusterDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(SZClusterDTO.class);
        SZClusterDTO sZClusterDTO1 = new SZClusterDTO();
        sZClusterDTO1.setId(1L);
        SZClusterDTO sZClusterDTO2 = new SZClusterDTO();
        assertThat(sZClusterDTO1).isNotEqualTo(sZClusterDTO2);
        sZClusterDTO2.setId(sZClusterDTO1.getId());
        assertThat(sZClusterDTO1).isEqualTo(sZClusterDTO2);
        sZClusterDTO2.setId(2L);
        assertThat(sZClusterDTO1).isNotEqualTo(sZClusterDTO2);
        sZClusterDTO1.setId(null);
        assertThat(sZClusterDTO1).isNotEqualTo(sZClusterDTO2);
    }
}
