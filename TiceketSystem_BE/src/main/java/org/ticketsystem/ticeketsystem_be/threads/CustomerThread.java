package org.ticketsystem.ticeketsystem_be.threads;

import org.ticketsystem.ticeketsystem_be.Entity.Customer;
import org.ticketsystem.ticeketsystem_be.Entity.Ticket;
import org.ticketsystem.ticeketsystem_be.dto.ConfigurationDTO;
import org.ticketsystem.ticeketsystem_be.repositories.TicketRepo;

public class CustomerThread implements Runnable {

    private TicketRepo ticketRepo;

    private final Customer customer;
    private final TicketPool ticketPool;
    private final int customerRetrievalRate;
    private volatile boolean running = true;
    private final int customerID;
    private final int ticketID;

    public CustomerThread(Customer customer, TicketPool ticketPool, int customerID, int ticketID, ConfigurationDTO configurationDTO) {
        this.customer = customer;
        this.ticketPool = ticketPool;
        this.customerID = customerID;
        this.ticketID = ticketID;
        this.customerRetrievalRate = configurationDTO.getCustomerRetrievalRate();
    }

    @Override
    public void run() {
        while (running) {
            try {
                Ticket ticket = ticketPool.removeTicket(ticketID,customerID);
                if (ticket != null) {
                    ticket.setSold(true);
                    ticketRepo.save(ticket);
                    System.out.println("Customer " + customer.getName() + " purchased ticket: " + ticket.getId());
                }
                Thread.sleep(customerRetrievalRate);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }

    public void stop() {
        running = false;
    }

    public int getCustomerID() {
        return customerID;
    }
}
