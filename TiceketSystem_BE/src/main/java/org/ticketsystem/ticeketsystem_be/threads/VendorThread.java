package org.ticketsystem.ticeketsystem_be.threads;

import org.ticketsystem.ticeketsystem_be.Entity.Ticket;
import org.ticketsystem.ticeketsystem_be.Entity.Vendor;
import org.ticketsystem.ticeketsystem_be.dto.ConfigurationDTO;
import org.ticketsystem.ticeketsystem_be.dto.PurchaseDTO;
import org.ticketsystem.ticeketsystem_be.repositories.TicketRepo;

import java.util.Date;

public class VendorThread implements Runnable {

    private final TicketRepo ticketRepo;
    private final TicketPool ticketPool;
    private final Vendor vendor;
    private final int totalTickets;
    private final int ticketReleaseRate;
    private volatile boolean running = true;
    private final int vendorID;
    private final PurchaseDTO purchaseDTO;

    public VendorThread(TicketRepo ticketRepo, TicketPool ticketPool, Vendor vendor, ConfigurationDTO configurationDTO ,PurchaseDTO purchaseDTO) {
        this.ticketRepo = ticketRepo;
        this.ticketPool = ticketPool;
        this.vendor = vendor;
        this.totalTickets = configurationDTO.getTotalTickets();
        this.ticketReleaseRate = configurationDTO.getTicketReleaseRate();
        this.vendorID = vendor.getId();
        this.purchaseDTO = purchaseDTO;
    }


    @Override
    public void run() {

        try{
            int ticketsAdded = 0;
            int remainingCapacity = ticketPool.getRemainingCapacity(vendorID);
            int ticketsToAdd = Math.min(purchaseDTO.getTicketCount(), remainingCapacity);

            for (int i = 0; i < ticketsToAdd && running; i++) {

                Ticket ticket = new Ticket();
                ticket.setName("Ticket " + (i + ticketsAdded));
                ticket.setPrice(purchaseDTO.getPrice());
                ticket.setSold(false);
                ticket.setDate(new Date());
                ticket.setVendor(vendor);
                ticket.setTicketType(purchaseDTO.getTicketType());

                ticketRepo.save(ticket);

                if (ticketPool.addTicket(vendorID, ticket)){
                    ticketsAdded++;
                    System.out.println("Ticket added by vendor" + vendorID+ "with ID " + ticket.getId());
                } else {
                    System.out.println("Vendor " + vendorID + " cannot add more tickets.");
                    break;
                }
                Thread.sleep(ticketReleaseRate);
            }
            System.out.println("Vendor " + vendorID + " thread has stopped after adding requested tickets.");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

    }

    public void stop() {
        running = false;
    }

    public int getVendorID() {
        return vendorID;
    }
}
