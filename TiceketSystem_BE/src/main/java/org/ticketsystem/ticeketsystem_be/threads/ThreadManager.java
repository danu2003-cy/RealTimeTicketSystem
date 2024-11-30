package org.ticketsystem.ticeketsystem_be.threads;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.ticketsystem.ticeketsystem_be.Entity.Vendor;
import org.ticketsystem.ticeketsystem_be.dto.ConfigurationDTO;
import org.ticketsystem.ticeketsystem_be.dto.PurchaseDTO;
import org.ticketsystem.ticeketsystem_be.repositories.TicketRepo;
import org.ticketsystem.ticeketsystem_be.repositories.VendorRepo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ThreadManager {

    private final VendorRepo vendorRepo;
    private final TicketRepo ticketRepo;
    private final List<VendorThread> vendorThreadList;
    private final ConfigurationDTO configurationDTO;
    private final Map<Integer, Vendor> vendorMap;
    private final TicketPool ticketPool;

    @Autowired
    public ThreadManager(VendorRepo vendorRepo, TicketRepo ticketRepo, ConfigurationDTO configurationDTO , TicketPool ticketPool, TicketPool ticketPool1) {
        this.vendorRepo = vendorRepo;
        this.ticketRepo = ticketRepo;
        this.vendorThreadList = new ArrayList<>();
        this.configurationDTO = configurationDTO;
        this.vendorMap = new HashMap<>();
        this.ticketPool = ticketPool;

    }

    public void addVendor(Vendor vendor) {vendorMap.put(vendor.getId(), vendor);}


    public String startVendorThread(int vendorId, PurchaseDTO purchaseDTO) {
        Vendor vendor = vendorMap.get(vendorId);

        if (vendor == null) {
            vendor = vendorRepo.findById(vendorId).orElse(null);
            if (vendor != null) {
                vendorMap.put(vendorId, vendor);
            }else {
                System.out.println("Vendor with id " + vendorId + " not found");
                return "Vendor with id " + vendorId + " not found";
            }
        }

        //Initiate and start the vendor thread
        VendorThread vendorThread = new VendorThread(ticketRepo, ticketPool, vendor, configurationDTO, purchaseDTO);
        vendorThreadList.add(vendorThread);
        new Thread(vendorThread).start();
        System.out.println("Open a new Thread for vendor: " + vendor.getName());
        return "Open a new Thread for vendor: " + vendor.getName();
    }

    public void stopVendorThread(int vendorId) {
        for (VendorThread vendorThread : vendorThreadList) {
            if (vendorThread.getVendorID() == vendorId) {
                vendorThread.stop();
                System.out.println("Vendor thread stopped for: " + vendorId);
                return;
            }
        }
        System.out.println("Vendor Thread Not Found for ID: " + vendorId);
    }

    public void stopAllVendorThreads() {
        for (VendorThread vendorThread : vendorThreadList) {
            vendorThread.stop();
        }
        System.out.println("All vendor threads have been stopped.");
    }

}
