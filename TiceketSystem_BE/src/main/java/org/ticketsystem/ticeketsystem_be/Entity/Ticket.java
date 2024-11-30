package org.ticketsystem.ticeketsystem_be.Entity;
import org.ticketsystem.ticeketsystem_be.Util.EventType;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;


@Entity
@Table(name = "ticket")
public class Ticket {

    @Id
    @Column(name = "ticket_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int ticketId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "event_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private EventType ticketType;

    @Column(name = "price", nullable = false)
    private double price;

    @Column(name = "sold", nullable = false)
    private boolean sold;

    @Column(name = "create_date", nullable = false, columnDefinition = "DATETIME")
    private Date date;

    @ManyToOne
    @JoinColumn(name = "vendor_id", nullable = false)
    private Vendor vendor;

    @OneToMany(mappedBy = "customer")
    private Set<Purchase> detailsSet;

    public Ticket() {
    }

    public Ticket(int ticketId, String name, EventType ticketType, double price, boolean sold, Date date, Vendor vendor, Set<Purchase> detailsSet) {
        this.ticketId = ticketId;
        this.name = name;
        this.ticketType = ticketType;
        this.price = price;
        this.sold = sold;
        this.date = date;
        this.vendor = vendor;
        this.detailsSet = detailsSet;
    }

    public Ticket(int ticketId, String name, EventType ticketType, double price, boolean sold, Date date, Vendor vendor) {
        this.ticketId = ticketId;
        this.name = name;
        this.ticketType = ticketType;
        this.price = price;
        this.sold = sold;
        this.date = date;
        this.vendor = vendor;
    }

    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public EventType getTicketType() {
        return ticketType;
    }

    public void setTicketType(EventType ticketType) {
        this.ticketType = ticketType;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isSold() {
        return sold;
    }

    public void setSold(boolean sold) {
        this.sold = sold;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Vendor getVendor() {
        return vendor;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }

    public Set<Purchase> getDetailsSet() {
        return detailsSet;
    }

    public void setDetailsSet(Set<Purchase> detailsSet) {
        this.detailsSet = detailsSet;
    }
}

