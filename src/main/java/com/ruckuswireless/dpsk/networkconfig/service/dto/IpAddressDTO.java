package com.ruckuswireless.dpsk.networkconfig.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link com.ruckuswireless.dpsk.networkconfig.domain.IpAddress} entity.
 */
public class IpAddressDTO implements Serializable {
    
    private Long id;

    private String ipAdress;


    private Long szClusterId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIpAdress() {
        return ipAdress;
    }

    public void setIpAdress(String ipAdress) {
        this.ipAdress = ipAdress;
    }

    public Long getSzClusterId() {
        return szClusterId;
    }

    public void setSzClusterId(Long sZClusterId) {
        this.szClusterId = sZClusterId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof IpAddressDTO)) {
            return false;
        }

        return id != null && id.equals(((IpAddressDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "IpAddressDTO{" +
            "id=" + getId() +
            ", ipAdress='" + getIpAdress() + "'" +
            ", szClusterId=" + getSzClusterId() +
            "}";
    }
}
