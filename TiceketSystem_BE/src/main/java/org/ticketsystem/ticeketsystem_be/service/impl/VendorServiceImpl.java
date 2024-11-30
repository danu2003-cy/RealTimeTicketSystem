package org.ticketsystem.ticeketsystem_be.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ticketsystem.ticeketsystem_be.Entity.Vendor;
import org.ticketsystem.ticeketsystem_be.dto.ConfigurationDTO;
import org.ticketsystem.ticeketsystem_be.dto.PurchaseDTO;
import org.ticketsystem.ticeketsystem_be.dto.VendorDTO;
import org.ticketsystem.ticeketsystem_be.repositories.VendorRepo;
import org.ticketsystem.ticeketsystem_be.service.VendorService;
import org.ticketsystem.ticeketsystem_be.threads.ThreadManager;

import java.util.List;

@Service
public class VendorServiceImpl implements VendorService {

    @Autowired
    private VendorRepo vendorRepo;

    @Autowired
    private ThreadManager threadManager;

    private final ConfigurationDTO configurationDTO;

    public VendorServiceImpl(ConfigurationDTO configurationDTO) {
        this.configurationDTO = configurationDTO;
    }

    @Override
    public String addVendor(VendorDTO vendorDTO) {

        Vendor vendor = new Vendor();
        vendor.setName(vendorDTO.getName());
        vendor.setTicketsPerRelease(configurationDTO.getTicketReleaseRate());
        vendorRepo.save(vendor);
        threadManager.addVendor(vendor);

        return "New vendor added...!";
    }

    @Override
    public String updateVendor(int vendorId, VendorDTO vendorDTO) {
        return null;
    }

    @Override
    public List<VendorDTO> getAllVendors() {
        return null;
    }

    @Override
    public String deleteVendor(int vendorID) {
        return null;
    }

    @Override
    public String startVendorThread(int vendorID, PurchaseDTO purchaseDTO) {

        String message = threadManager.startVendorThread(vendorID, purchaseDTO);
        return message;
    }

    @Override
    public String stopVendorThread(int vendorID){

        threadManager.stopVendorThread(vendorID);
        return "vendor thread stopped...";
    }

}
