package com.ruckuswireless.dpsk.networkconfig.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A SZCluster.
 */
@Entity
@Table(name = "sz_cluster")
public class SZCluster implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "shared_secret")
    private String sharedSecret;

    @OneToMany(mappedBy = "szCluster")
    private Set<IpAddress> ipAddresses = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = "sZClusters", allowSetters = true)
    private Infrastructure infrastructure;

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

    public SZCluster name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSharedSecret() {
        return sharedSecret;
    }

    public SZCluster sharedSecret(String sharedSecret) {
        this.sharedSecret = sharedSecret;
        return this;
    }

    public void setSharedSecret(String sharedSecret) {
        this.sharedSecret = sharedSecret;
    }

    public Set<IpAddress> getIpAddresses() {
        return ipAddresses;
    }

    public SZCluster ipAddresses(Set<IpAddress> ipAddresses) {
        this.ipAddresses = ipAddresses;
        return this;
    }

    public SZCluster addIpAddress(IpAddress ipAddress) {
        this.ipAddresses.add(ipAddress);
        ipAddress.setSzCluster(this);
        return this;
    }

    public SZCluster removeIpAddress(IpAddress ipAddress) {
        this.ipAddresses.remove(ipAddress);
        ipAddress.setSzCluster(null);
        return this;
    }

    public void setIpAddresses(Set<IpAddress> ipAddresses) {
        this.ipAddresses = ipAddresses;
    }

    public Infrastructure getInfrastructure() {
        return infrastructure;
    }

    public SZCluster infrastructure(Infrastructure infrastructure) {
        this.infrastructure = infrastructure;
        return this;
    }

    public void setInfrastructure(Infrastructure infrastructure) {
        this.infrastructure = infrastructure;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SZCluster)) {
            return false;
        }
        return id != null && id.equals(((SZCluster) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SZCluster{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", sharedSecret='" + getSharedSecret() + "'" +
            "}";
    }
}
