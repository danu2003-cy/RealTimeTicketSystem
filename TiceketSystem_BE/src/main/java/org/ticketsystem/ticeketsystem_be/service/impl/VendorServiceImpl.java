package org.ticketsystem.ticeketsystem_be.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ticketsystem.ticeketsystem_be.Entity.Vendor;
import org.ticketsystem.ticeketsystem_be.dto.ConfigurationDTO;
import org.ticketsystem.ticeketsystem_be.dto.PurchaseDTO;
import org.ticketsystem.ticeketsystem_be.dto.VendorDTO;
import org.ticketsystem.ticeketsystem_be.repositories.VendorRepo;
import org.ticketsystem.ticeketsystem_be.service.VendorService;
import org.ticketsystem.ticeketsystem_be.threads.ThreadManager;

import java.util.ArrayList;
import java.util.List;

@Slf4j
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
        log.info("Adding a new vendor: {}", vendorDTO.getName());

        Vendor vendor = new Vendor();
        vendor.setName(vendorDTO.getName());
        vendor.setTicketsPerRelease(configurationDTO.getTicketReleaseRate());
        vendorRepo.save(vendor);
        log.info("Vendor saved to database with ID: {}", vendor.getId());

        threadManager.addVendor(vendor);
        log.info("Vendor thread started for ID: {}", vendor.getId());

        return "New vendor added...!";
    }

    @Override
    public String updateVendor(int vendorId, VendorDTO vendorDTO) {
        log.info("Updating vendor with ID: {}", vendorId);

        // Find the existing vendor by its ID
        Vendor vendor = vendorRepo.findById(vendorId).orElse(null);

        if (vendor == null) {
            log.warn("Vendor with ID {} not found.", vendorId);
            return "Vendor not found!";
        }

        // Update the vendor's details based on the provided VendorDTO
        vendor.setName(vendorDTO.getName());
        vendor.setTicketsPerRelease(configurationDTO.getTicketReleaseRate());

        // Save the updated vendor
        vendorRepo.save(vendor);

        log.info("Vendor with ID {} updated successfully.", vendorId);
        return "Vendor updated successfully!";
    }

    @Override
    public List<VendorDTO> getAllVendors() {
        log.info("Fetching all vendors from the database.");
        // Retrieve all vendors from the database
        List<Vendor> vendors = vendorRepo.findAll();

        // Create a list to store VendorDTOs
        List<VendorDTO> vendorDTOList = new ArrayList<>();

        // Manually map each Vendor to VendorDTO
        for (Vendor vendor : vendors) {
            VendorDTO vendorDTO = new VendorDTO(vendor.getId(), vendor.getName(), vendor.getTicketsPerRelease());
            vendorDTOList.add(vendorDTO);
        }

        log.info("Total vendors fetched: {}", vendorDTOList.size());
        return vendorDTOList;
    }

    @Override
    public String deleteVendor(int vendorID) {
        log.info("Deleting vendor with ID: {}", vendorID);

        // Find the vendor by ID to ensure it exists before deletion
        Vendor vendor = vendorRepo.findById(vendorID).orElse(null);

        if (vendor == null) {
            log.warn("Vendor with ID {} not found.", vendorID);
            return "Vendor not found!";
        }

        // Remove the vendor from the database
        vendorRepo.delete(vendor);
        log.info("Vendor with ID {} deleted from database.", vendorID);

        // Optionally stop any running thread related to the vendor
        threadManager.stopVendorThread(vendorID);
        log.info("Thread for vendor ID {} stopped successfully.", vendorID);

        return "Vendor deleted successfully!";
    }

    //test

    @Override
    public String startVendorThread(int vendorID, PurchaseDTO purchaseDTO) {
        log.info("Starting vendor thread for ID: {}", vendorID);

        String message = threadManager.startVendorThread(vendorID, purchaseDTO);
        log.info("Vendor thread started for ID {} with message: {}", vendorID, message);

        return message;
    }

    @Override
    public String stopVendorThread(int vendorID){
        log.info("Stopping vendor thread for ID: {}", vendorID);

        threadManager.stopVendorThread(vendorID);
        log.info("Vendor thread stopped for ID: {}", vendorID);
        return "vendor thread stopped...";
    }

}
