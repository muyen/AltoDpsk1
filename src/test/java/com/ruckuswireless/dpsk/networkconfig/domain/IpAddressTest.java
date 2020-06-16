package com.ruckuswireless.dpsk.networkconfig.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.ruckuswireless.dpsk.networkconfig.web.rest.TestUtil;

public class IpAddressTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(IpAddress.class);
        IpAddress ipAddress1 = new IpAddress();
        ipAddress1.setId(1L);
        IpAddress ipAddress2 = new IpAddress();
        ipAddress2.setId(ipAddress1.getId());
        assertThat(ipAddress1).isEqualTo(ipAddress2);
        ipAddress2.setId(2L);
        assertThat(ipAddress1).isNotEqualTo(ipAddress2);
        ipAddress1.setId(null);
        assertThat(ipAddress1).isNotEqualTo(ipAddress2);
    }
}
