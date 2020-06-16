package com.ruckuswireless.dpsk.networkconfig.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A IpAddress.
 */
@Entity
@Table(name = "ip_address")
public class IpAddress implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "ip_adress")
    private String ipAdress;

    @ManyToOne
    @JsonIgnoreProperties(value = "ipAddresses", allowSetters = true)
    private SZCluster szCluster;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIpAdress() {
        return ipAdress;
    }

    public IpAddress ipAdress(String ipAdress) {
        this.ipAdress = ipAdress;
        return this;
    }

    public void setIpAdress(String ipAdress) {
        this.ipAdress = ipAdress;
    }

    public SZCluster getSzCluster() {
        return szCluster;
    }

    public IpAddress szCluster(SZCluster sZCluster) {
        this.szCluster = sZCluster;
        return this;
    }

    public void setSzCluster(SZCluster sZCluster) {
        this.szCluster = sZCluster;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof IpAddress)) {
            return false;
        }
        return id != null && id.equals(((IpAddress) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "IpAddress{" +
            "id=" + getId() +
            ", ipAdress='" + getIpAdress() + "'" +
            "}";
    }
}
