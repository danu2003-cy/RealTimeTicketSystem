package org.example.oop_cw.ticket;

public class Vendor implements Runnable {
    private final int vendorId;
    private final int ticketsToAdd;
    private final TicketPool ticketPool;

    public Vendor(int vendorId, int ticketsToAdd, TicketPool ticketPool) {
        this.vendorId = vendorId;
        this.ticketsToAdd = ticketsToAdd;
        this.ticketPool = ticketPool;
    }

    @Override
    public void run() {
        synchronized (ticketPool) {
            if (ticketPool.getAvailableTickets() < ticketPool.getMaxCapacity()) {
                ticketPool.addTickets(ticketsToAdd);
                System.out.println("Vendor " + vendorId + ": Added " + ticketsToAdd + " tickets.");
            } else {
                System.out.println("Vendor " + vendorId + ": Ticket pool already full. Exiting.");
            }
        }
        System.out.println("Vendor " + vendorId + " has stopped.");
    }
}
