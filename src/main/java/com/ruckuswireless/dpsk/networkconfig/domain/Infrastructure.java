package com.ruckuswireless.dpsk.networkconfig.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Infrastructure.
 */
@Entity
@Table(name = "infrastructure")
public class Infrastructure implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToOne
    @JoinColumn(unique = true)
    private AuthorizationServer authorizationServer;

    /**
     * A relationship
     */
    @OneToMany(mappedBy = "infrastructure")
    private Set<SZCluster> sZClusters = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Infrastructure name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AuthorizationServer getAuthorizationServer() {
        return authorizationServer;
    }

    public Infrastructure authorizationServer(AuthorizationServer authorizationServer) {
        this.authorizationServer = authorizationServer;
        return this;
    }

    public void setAuthorizationServer(AuthorizationServer authorizationServer) {
        this.authorizationServer = authorizationServer;
    }

    public Set<SZCluster> getSZClusters() {
        return sZClusters;
    }

    public Infrastructure sZClusters(Set<SZCluster> sZClusters) {
        this.sZClusters = sZClusters;
        return this;
    }

    public Infrastructure addSZCluster(SZCluster sZCluster) {
        this.sZClusters.add(sZCluster);
        sZCluster.setInfrastructure(this);
        return this;
    }

    public Infrastructure removeSZCluster(SZCluster sZCluster) {
        this.sZClusters.remove(sZCluster);
        sZCluster.setInfrastructure(null);
        return this;
    }

    public void setSZClusters(Set<SZCluster> sZClusters) {
        this.sZClusters = sZClusters;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Infrastructure)) {
            return false;
        }
        return id != null && id.equals(((Infrastructure) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Infrastructure{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            "}";
    }
}
