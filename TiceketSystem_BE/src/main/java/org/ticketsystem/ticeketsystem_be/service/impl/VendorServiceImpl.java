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

import java.util.ArrayList;
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
        // Find the existing vendor by its ID
        Vendor vendor = vendorRepo.findById(vendorId).orElse(null);

        if (vendor == null) {
            return "Vendor not found!";
        }

        // Update the vendor's details based on the provided VendorDTO
        vendor.setName(vendorDTO.getName());
        vendor.setTicketsPerRelease(configurationDTO.getTicketReleaseRate());

        // Save the updated vendor
        vendorRepo.save(vendor);

        return "Vendor updated successfully!";
    }

    @Override
    public List<VendorDTO> getAllVendors() {
        // Retrieve all vendors from the database
        List<Vendor> vendors = vendorRepo.findAll();

        // Create a list to store VendorDTOs
        List<VendorDTO> vendorDTOList = new ArrayList<>();

        // Manually map each Vendor to VendorDTO
        for (Vendor vendor : vendors) {
            VendorDTO vendorDTO = new VendorDTO(vendor.getId(), vendor.getName(), vendor.getTicketsPerRelease());
            vendorDTOList.add(vendorDTO);
        }

        return vendorDTOList;
    }

    @Override
    public String deleteVendor(int vendorID) {
        // Find the vendor by ID to ensure it exists before deletion
        Vendor vendor = vendorRepo.findById(vendorID).orElse(null);

        if (vendor == null) {
            return "Vendor not found!";
        }

        // Remove the vendor from the database
        vendorRepo.delete(vendor);

        // Optionally stop any running thread related to the vendor
        threadManager.stopVendorThread(vendorID);

        return "Vendor deleted successfully!";
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
