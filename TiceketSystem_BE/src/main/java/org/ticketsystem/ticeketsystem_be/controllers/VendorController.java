package org.ticketsystem.ticeketsystem_be.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.ticketsystem.ticeketsystem_be.Util.StandardResponse;
import org.ticketsystem.ticeketsystem_be.dto.PurchaseDTO;
import org.ticketsystem.ticeketsystem_be.dto.VendorDTO;
import org.ticketsystem.ticeketsystem_be.service.VendorService;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("vendor")
public class VendorController {

    @Autowired
    private VendorService vendorService;

    @PostMapping(path = "/add-vendor")
    public ResponseEntity<StandardResponse> addVendor(@RequestBody VendorDTO vendorDTO) {
        String data = vendorService.addVendor(vendorDTO);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(201,"success",data), HttpStatus.CREATED
        );
    }

    @PutMapping(path = "/update-vendor", params = {"vendorID"})
    public ResponseEntity<StandardResponse> updateVendor
            (@RequestBody VendorDTO vendorDTO, @RequestParam(name = "vendorID") int vendorId) {
        String data = vendorService.updateVendor(vendorId, vendorDTO);
        return new ResponseEntity<StandardResponse>(new StandardResponse(200,"success",data), HttpStatus.CREATED
        );
                
    }

    @GetMapping(path = "/get-all-vendors")
    public ResponseEntity<StandardResponse> getAllVendors(){
        List<VendorDTO> vendorList =vendorService.getAllVendors();
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(201,"Success",vendorList),HttpStatus.CREATED
        );
    }

    @DeleteMapping(path = "/delete-vendor",params = {"vendorID"})
    public ResponseEntity<StandardResponse> deleteVendeor(@RequestParam(name = "vendorID") int vendorID){
        String data = vendorService.deleteVendor(vendorID);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(201,"Success",data),HttpStatus.CREATED
        );
    }

    @PostMapping(path = "/start-vendor-thread",params = {"vendorID"})
    public ResponseEntity<StandardResponse> startVendorThread(
            @RequestParam(name = "vendorID") int vendorID, @RequestBody PurchaseDTO purchaseDTO)
    {
        String data = vendorService.startVendorThread(vendorID,purchaseDTO);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(201,"Success",data),HttpStatus.CREATED
        );
    }

    @GetMapping(path = "/stop-vendor-thread",params = {"vendorID"})
    public ResponseEntity<StandardResponse> stopVendorThread(@RequestParam(name = "vendorID") int vendorID){
        String data = vendorService.stopVendorThread(vendorID);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(201,"Success",data),HttpStatus.CREATED
        );
    }
}
