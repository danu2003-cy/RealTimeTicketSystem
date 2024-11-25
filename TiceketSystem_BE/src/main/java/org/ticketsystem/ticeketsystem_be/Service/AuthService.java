package org.ticketsystem.ticeketsystem_be.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ticketsystem.ticeketsystem_be.dto.CustomerRegistrationDto;
import org.ticketsystem.ticeketsystem_be.dto.LoginDto;
import org.ticketsystem.ticeketsystem_be.dto.VendorRegistrationDto;
import org.ticketsystem.ticeketsystem_be.models.Customer;
import org.ticketsystem.ticeketsystem_be.models.Vendor;
import org.ticketsystem.ticeketsystem_be.repositories.CustomerRepository;
import org.ticketsystem.ticeketsystem_be.repositories.VendorRepository;

@Service
public class AuthService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private VendorRepository vendorRepository;

    public Customer registerCustomer(CustomerRegistrationDto dto) {
        if (customerRepository.findByUsername(dto.getUsername()).isPresent()) {
            throw new RuntimeException("Username already exists");
        }
        Customer customer = new Customer();
        customer.setName(dto.getName());
        customer.setEmail(dto.getEmail());
        customer.setContactNumber(dto.getContactNumber());
        customer.setUsername(dto.getUsername());
        customer.setPassword(dto.getPassword()); // Hash this for security
        return customerRepository.save(customer);
    }

    public Vendor registerVendor(VendorRegistrationDto dto) {
        if (vendorRepository.findByUsername(dto.getUsername()).isPresent()) {
            throw new RuntimeException("Username already exists");
        }
        Vendor vendor = new Vendor();
        vendor.setName(dto.getName());
        vendor.setEmail(dto.getEmail());
        vendor.setContactNumber(dto.getContactNumber());
        vendor.setUsername(dto.getUsername());
        vendor.setPassword(dto.getPassword()); // Hash this for security
        return vendorRepository.save(vendor);
    }

    public Object login(LoginDto dto) {
        if (dto.getRole().equalsIgnoreCase("customer")) {
            Customer customer = customerRepository.findByUsername(dto.getUsername())
                    .orElseThrow(() -> new RuntimeException("Invalid username or password"));
            if (customer.getPassword().equals(dto.getPassword())) { // Compare hashed passwords
                return customer;
            }
        } else if (dto.getRole().equalsIgnoreCase("vendor")) {
            Vendor vendor = vendorRepository.findByUsername(dto.getUsername())
                    .orElseThrow(() -> new RuntimeException("Invalid username or password"));
            if (vendor.getPassword().equals(dto.getPassword())) { // Compare hashed passwords
                return vendor;
            }
        }
        throw new RuntimeException("Invalid username or password");
    }
}
