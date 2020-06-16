package com.ruckuswireless.dpsk.networkconfig.domain;


import javax.persistence.*;

import java.io.Serializable;

/**
 * A AuthorizationServer.
 */
@Entity
@Table(name = "authorization_server")
public class AuthorizationServer implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @OneToOne
    @JoinColumn(unique = true)
    private AuthConfig primaryServer;

    @OneToOne
    @JoinColumn(unique = true)
    private AuthConfig secondaryServer;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AuthConfig getPrimaryServer() {
        return primaryServer;
    }

    public AuthorizationServer primaryServer(AuthConfig authConfig) {
        this.primaryServer = authConfig;
        return this;
    }

    public void setPrimaryServer(AuthConfig authConfig) {
        this.primaryServer = authConfig;
    }

    public AuthConfig getSecondaryServer() {
        return secondaryServer;
    }

    public AuthorizationServer secondaryServer(AuthConfig authConfig) {
        this.secondaryServer = authConfig;
        return this;
    }

    public void setSecondaryServer(AuthConfig authConfig) {
        this.secondaryServer = authConfig;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AuthorizationServer)) {
            return false;
        }
        return id != null && id.equals(((AuthorizationServer) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AuthorizationServer{" +
            "id=" + getId() +
            "}";
    }
}
