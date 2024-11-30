package org.ticketsystem.ticeketsystem_be.dto;

import org.ticketsystem.ticeketsystem_be.Entity.Purchase;
import org.ticketsystem.ticeketsystem_be.Entity.Vendor;
import org.ticketsystem.ticeketsystem_be.Util.EventType;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

public class TicketDTO {

    private int id;

    private String name;

    private double price;

    private boolean sold;

    private Vendor vendorID;

    private EventType ticketType;

    public TicketDTO() {
    }

    public TicketDTO(int id, String name, double price, boolean sold, Vendor vendorID, EventType ticketType) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.sold = sold;
        this.vendorID = vendorID;
        this.ticketType = ticketType;
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

    public Vendor getVendorID() {
        return vendorID;
    }

    public void setVendorID(Vendor vendorID) {
        this.vendorID = vendorID;
    }

    public EventType getTicketType() {
        return ticketType;
    }

    public void setTicketType(EventType ticketType) {
        this.ticketType = ticketType;
    }
}
