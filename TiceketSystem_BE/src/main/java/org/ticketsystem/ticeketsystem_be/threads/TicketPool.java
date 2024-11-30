package org.ticketsystem.ticeketsystem_be.threads;

import org.springframework.stereotype.Component;
import org.ticketsystem.ticeketsystem_be.Entity.Ticket;
import org.ticketsystem.ticeketsystem_be.dto.ConfigurationDTO;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class TicketPool {

    private final Map<Integer, Ticket> tickets;
    private final Map<Integer, AtomicInteger> vendorTicketCounts;
    private final int maxTicketCapacity;
    private final int totalTicketCount;
    private final int maxVendorCount;
    private final AtomicInteger currentVendorCount;

    public TicketPool(ConfigurationDTO configurationDTO) {
        this.tickets = new ConcurrentHashMap<>(configurationDTO.getMaxTicketCapacity());
        this.maxTicketCapacity = configurationDTO.getMaxTicketCapacity();
        this.totalTicketCount = configurationDTO.getTotalTickets();
        this.maxVendorCount = totalTicketCount / maxTicketCapacity;
        this.currentVendorCount = new AtomicInteger(0);
        this.vendorTicketCounts = new ConcurrentHashMap<>();
    }


    public boolean addTicket(int vendorID, Ticket ticket) {
        // Check if the vendor count exceeds the maximum allowed vendors
        if (currentVendorCount.get() >= maxVendorCount && !vendorTicketCounts.containsKey(vendorID)) {
            System.out.println("Cannot add ticket. Maximum vendor count reached.");
            return false;
        }

        // Initialize vendor ticket count if it doesn't exist yet
        vendorTicketCounts.putIfAbsent(vendorID, new AtomicInteger(0));
        if (vendorTicketCounts.get(vendorID).get() == 0) {
            currentVendorCount.incrementAndGet();
        }

        AtomicInteger vendorCount = vendorTicketCounts.get(vendorID);

        // Ensure the vendor's ticket count does not exceed the maximum allowed capacity
        if (vendorCount.get() < maxTicketCapacity) {
            tickets.put(ticket.getId(), ticket);
            vendorCount.incrementAndGet();
            System.out.println("Ticket added by Vendor " + vendorID + ": " + ticket.getName());
            return true;
        } else {
            System.out.println("Vendor " + vendorID + " has reached the maximum ticket capacity.");
            return false;
        }

    }


    public Ticket removeTicket(int ticketID, int customerID) throws InterruptedException {
        System.out.println("Attempting to remove ticket with ID: " + ticketID);
        System.out.println("Current tickets in pool: " + tickets.keySet());
        Ticket ticket = tickets.get(ticketID);
        if (ticket != null) {
            if (!ticket.isSold()) {
                tickets.remove(ticketID);
                ticket.setSold(true);
                System.out.println("Ticket purchased by customer " + customerID + ": " + ticket.getName());
                return ticket;
            } else {
                System.out.println("Ticket with ID " + ticketID + " is already sold.");
            }
        } else {
            System.out.println("Ticket with ID " + ticketID + " not found in the pool.");
        }
        return null;
    }

    public synchronized int getAvailableTickets() {
        System.out.println(tickets.size());
        return tickets.size();
    }

    public int getRemainingCapacity(int vendorID) {
        return maxTicketCapacity - vendorTicketCounts.getOrDefault(vendorID, new AtomicInteger(0)).get();

    }

}
