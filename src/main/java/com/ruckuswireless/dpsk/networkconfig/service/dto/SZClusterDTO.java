package com.ruckuswireless.dpsk.networkconfig.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link com.ruckuswireless.dpsk.networkconfig.domain.SZCluster} entity.
 */
public class SZClusterDTO implements Serializable {
    
    private Long id;

    private String name;

    private String sharedSecret;


    private Long infrastructureId;
    
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

    public String getSharedSecret() {
        return sharedSecret;
    }

    public void setSharedSecret(String sharedSecret) {
        this.sharedSecret = sharedSecret;
    }

    public Long getInfrastructureId() {
        return infrastructureId;
    }

    public void setInfrastructureId(Long infrastructureId) {
        this.infrastructureId = infrastructureId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SZClusterDTO)) {
            return false;
        }

        return id != null && id.equals(((SZClusterDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SZClusterDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", sharedSecret='" + getSharedSecret() + "'" +
            ", infrastructureId=" + getInfrastructureId() +
            "}";
    }
}
