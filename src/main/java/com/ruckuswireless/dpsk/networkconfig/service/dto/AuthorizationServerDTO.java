package com.ruckuswireless.dpsk.networkconfig.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link com.ruckuswireless.dpsk.networkconfig.domain.AuthorizationServer} entity.
 */
public class AuthorizationServerDTO implements Serializable {
    
    private Long id;


    private Long primaryServerId;

    private Long secondaryServerId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPrimaryServerId() {
        return primaryServerId;
    }

    public void setPrimaryServerId(Long authConfigId) {
        this.primaryServerId = authConfigId;
    }

    public Long getSecondaryServerId() {
        return secondaryServerId;
    }

    public void setSecondaryServerId(Long authConfigId) {
        this.secondaryServerId = authConfigId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AuthorizationServerDTO)) {
            return false;
        }

        return id != null && id.equals(((AuthorizationServerDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AuthorizationServerDTO{" +
            "id=" + getId() +
            ", primaryServerId=" + getPrimaryServerId() +
            ", secondaryServerId=" + getSecondaryServerId() +
            "}";
    }
}
