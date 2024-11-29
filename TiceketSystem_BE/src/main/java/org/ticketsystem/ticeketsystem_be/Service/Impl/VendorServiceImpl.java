package org.ticketsystem.ticeketsystem_be.Service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ticketsystem.ticeketsystem_be.repositories.VendorRepository;

@Service
public class VendorServiceImpl {

    @Autowired
    private VendorRepository vendorRepository;

}
