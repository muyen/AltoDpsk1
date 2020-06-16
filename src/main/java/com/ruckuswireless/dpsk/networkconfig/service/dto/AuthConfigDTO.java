package com.ruckuswireless.dpsk.networkconfig.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link com.ruckuswireless.dpsk.networkconfig.domain.AuthConfig} entity.
 */
public class AuthConfigDTO implements Serializable {
    
    private Long id;

    private String name;

    private String ipAddress;

    private Integer port;

    private String sharedSecret;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getSharedSecret() {
        return sharedSecret;
    }

    public void setSharedSecret(String sharedSecret) {
        this.sharedSecret = sharedSecret;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AuthConfigDTO)) {
            return false;
        }

        return id != null && id.equals(((AuthConfigDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AuthConfigDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", ipAddress='" + getIpAddress() + "'" +
            ", port=" + getPort() +
            ", sharedSecret='" + getSharedSecret() + "'" +
            "}";
    }
}
