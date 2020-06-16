package com.ruckuswireless.dpsk.networkconfig.service.dto;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

/**
 * A DTO for the {@link com.ruckuswireless.dpsk.networkconfig.domain.Infrastructure} entity.
 */
public class InfrastructureDTO implements Serializable {
    
    private Long id;

    private String name;


    private Long authorizationServerId;
    /**
     * A relationship
     */
    @ApiModelProperty(value = "A relationship")
    
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

    public Long getAuthorizationServerId() {
        return authorizationServerId;
    }

    public void setAuthorizationServerId(Long authorizationServerId) {
        this.authorizationServerId = authorizationServerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof InfrastructureDTO)) {
            return false;
        }

        return id != null && id.equals(((InfrastructureDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "InfrastructureDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", authorizationServerId=" + getAuthorizationServerId() +
            "}";
    }
}
