package org.ticketsystem.ticeketsystem_be.service;

import org.ticketsystem.ticeketsystem_be.dto.PurchaseDTO;
import org.ticketsystem.ticeketsystem_be.dto.VendorDTO;

import java.util.List;

public interface VendorService {
    String addVendor(VendorDTO vendorDTO);

    String updateVendor(int vendorId, VendorDTO vendorDTO);

    List<VendorDTO> getAllVendors();

    String deleteVendor(int vendorID);

    String startVendorThread(int vendorID, PurchaseDTO purchaseDTO);

    String stopVendorThread(int vendorID);
}
