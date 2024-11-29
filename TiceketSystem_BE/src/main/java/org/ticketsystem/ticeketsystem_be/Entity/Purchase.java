package org.ticketsystem.ticeketsystem_be.Entity;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "Purchase_details")
public class Purchase {

    @Id
    @Column(name = "purchase_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int purchaseID;

    @Column(name = "purchase_date", nullable = false, columnDefinition = "DATETIME")
    private Date date;

    @Column(name = "ticket_type", nullable = false)
    private String ticketType;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "ticket_id", nullable = false)
    private Ticket ticket;


    public Purchase() {
    }

    public Purchase(int purchaseID, Date date, String ticketType, Customer customer, Ticket ticket) {
        this.purchaseID = purchaseID;
        this.date = date;
        this.ticketType = ticketType;
        this.customer = customer;
        this.ticket = ticket;
    }

    public int getPurchaseID() {
        return purchaseID;
    }

    public void setPurchaseID(int purchaseID) {
        this.purchaseID = purchaseID;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTicketType() {
        return ticketType;
    }

    public void setTicketType(String ticketType) {
        this.ticketType = ticketType;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }
}