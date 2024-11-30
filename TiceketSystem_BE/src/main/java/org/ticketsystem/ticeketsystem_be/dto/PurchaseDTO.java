package org.ticketsystem.ticeketsystem_be.dto;

import org.ticketsystem.ticeketsystem_be.Util.EventType;

public class PurchaseDTO {

    private double price;
    private EventType ticketType;
    private int ticketCount;

    public PurchaseDTO() {
    }

    public PurchaseDTO(double price, EventType ticketType, int ticketCount) {
        this.price = price;
        this.ticketType = ticketType;
        this.ticketCount = ticketCount;
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

    public int getTicketCount() {
        return ticketCount;
    }

    public void setTicketCount(int ticketCount) {
        this.ticketCount = ticketCount;
    }
}
