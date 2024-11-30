package org.ticketsystem.ticeketsystem_be.Entity;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @Column(name = "customer_id",nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int customerId;

    @Column(name = "name",nullable = false,length = 100)
    private String name;

    @Column(name = "retrieval_interval")
    private int retrievalInterval;

    @Column(name = "is_vip",nullable = false)
    private boolean isVIP;

    @OneToMany(mappedBy = "customer")
    private Set<Purchase> detailsSet;


    public Customer() {
    }

    public Customer(int customerId, String name, int retrievalInterval, boolean isVIP, Set<Purchase> detailsSet) {
        this.customerId = customerId;
        this.name = name;
        this.retrievalInterval = retrievalInterval;
        this.isVIP = isVIP;
        this.detailsSet = detailsSet;
    }

    public Customer(int customerId, String name, int retrievalInterval, boolean isVIP) {
        this.customerId = customerId;
        this.name = name;
        this.retrievalInterval = retrievalInterval;
        this.isVIP = isVIP;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRetrievalInterval() {
        return retrievalInterval;
    }

    public void setRetrievalInterval(int retrievalInterval) {
        this.retrievalInterval = retrievalInterval;
    }

    public boolean isVIP() {
        return isVIP;
    }

    public void setVIP(boolean VIP) {
        isVIP = VIP;
    }

    public Set<Purchase> getDetailsSet() {
        return detailsSet;
    }

    public void setDetailsSet(Set<Purchase> detailsSet) {
        this.detailsSet = detailsSet;
    }
}
