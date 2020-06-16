package com.ruckuswireless.dpsk.networkconfig.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.ruckuswireless.dpsk.networkconfig.web.rest.TestUtil;

public class IpAddressDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(IpAddressDTO.class);
        IpAddressDTO ipAddressDTO1 = new IpAddressDTO();
        ipAddressDTO1.setId(1L);
        IpAddressDTO ipAddressDTO2 = new IpAddressDTO();
        assertThat(ipAddressDTO1).isNotEqualTo(ipAddressDTO2);
        ipAddressDTO2.setId(ipAddressDTO1.getId());
        assertThat(ipAddressDTO1).isEqualTo(ipAddressDTO2);
        ipAddressDTO2.setId(2L);
        assertThat(ipAddressDTO1).isNotEqualTo(ipAddressDTO2);
        ipAddressDTO1.setId(null);
        assertThat(ipAddressDTO1).isNotEqualTo(ipAddressDTO2);
    }
}
