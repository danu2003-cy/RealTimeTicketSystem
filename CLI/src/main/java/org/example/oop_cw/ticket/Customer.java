package org.example.oop_cw.ticket;

import org.example.oop_cw.CLI.TicketingSystemCLI;

public class Customer implements Runnable {
    private final int customerId;
    private final TicketPool ticketPool;
    private final int retrievalInterval;

    public Customer(int customerId, TicketPool ticketPool, int retrievalInterval) {
        this.customerId = customerId;
        this.ticketPool = ticketPool;
        this.retrievalInterval = retrievalInterval;
    }

    @Override
    public void run() {
        while (TicketingSystemCLI.isRunning() && !ticketPool.isSoldOut()) {
            if (ticketPool.removeTicket()) {
                System.out.println("Customer " + customerId + " successfully purchased a ticket.");
            }
            try {
                Thread.sleep(retrievalInterval);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
        System.out.println("Customer " + customerId + " has stopped.");
    }
}
