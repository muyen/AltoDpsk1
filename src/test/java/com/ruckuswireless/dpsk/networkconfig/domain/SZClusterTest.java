package com.ruckuswireless.dpsk.networkconfig.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.ruckuswireless.dpsk.networkconfig.web.rest.TestUtil;

public class SZClusterTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SZCluster.class);
        SZCluster sZCluster1 = new SZCluster();
        sZCluster1.setId(1L);
        SZCluster sZCluster2 = new SZCluster();
        sZCluster2.setId(sZCluster1.getId());
        assertThat(sZCluster1).isEqualTo(sZCluster2);
        sZCluster2.setId(2L);
        assertThat(sZCluster1).isNotEqualTo(sZCluster2);
        sZCluster1.setId(null);
        assertThat(sZCluster1).isNotEqualTo(sZCluster2);
    }
}
