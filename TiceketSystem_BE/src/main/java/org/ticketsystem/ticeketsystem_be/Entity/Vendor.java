package org.ticketsystem.ticeketsystem_be.Entity;

import javax.persistence.*;
import java.util.Set;


@Entity
@Table(name = "vendor")
public class Vendor {

    @Id
    @Column(name = "vendor_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "name",nullable = false)
    private String name;

    @Column(name = "tickets_per_release",nullable = false)
    private int ticketsPerRelease;


    @OneToMany(mappedBy = "vendor")
    private Set<Ticket> ticketSet;

    public Vendor() {
    }

    public Vendor(int id, String name, int ticketsPerRelease, Set<Ticket> ticketSet) {
        this.id = id;
        this.name = name;
        this.ticketsPerRelease = ticketsPerRelease;
        this.ticketSet = ticketSet;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTicketsPerRelease() {
        return ticketsPerRelease;
    }

    public void setTicketsPerRelease(int ticketsPerRelease) {
        this.ticketsPerRelease = ticketsPerRelease;
    }

    public Set<Ticket> getTicketSet() {
        return ticketSet;
    }

    public void setTicketSet(Set<Ticket> ticketSet) {
        this.ticketSet = ticketSet;
    }
}
