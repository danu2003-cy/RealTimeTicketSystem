package org.ticketsystem.ticeketsystem_be.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ticketsystem.ticeketsystem_be.Entity.Customer;
import org.ticketsystem.ticeketsystem_be.dto.ConfigurationDTO;
import org.ticketsystem.ticeketsystem_be.dto.CustomerDTO;
import org.ticketsystem.ticeketsystem_be.repositories.CustomerRepo;
import org.ticketsystem.ticeketsystem_be.service.CustomerService;
import org.ticketsystem.ticeketsystem_be.threads.TicketPool;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepo customerRepo;

    private final ConfigurationDTO configurationDTO;
    private final TicketPool ticketPool;

    public CustomerServiceImpl(ConfigurationDTO configurationDTO, TicketPool ticketPool) {
        this.configurationDTO = configurationDTO;
        this.ticketPool = ticketPool;
    }

    @Override
    public String addCustomer(CustomerDTO customerDTO) {
        log.info("Adding a new customer: {}", customerDTO.getName());

        Customer customer = new Customer();
        customer.setName(customerDTO.getName());
        customer.setRetrievalInterval(configurationDTO.getCustomerRetrievalRate());
        customer.setVIP(customerDTO.isVIP());

        customerRepo.save(customer);

        log.info("Customer added successfully: {}", customer.getName());
        return "Customer has been created successfully...!";
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {

        log.info("Fetching all customers from the database.");
        List<Customer> customers = customerRepo.findAll();
        List<CustomerDTO> customerDTOs = new ArrayList<>();
        for (Customer customer : customers) {
            customerDTOs.add(new CustomerDTO(
                    customer.getCustomerId(),
                    customer.getName(),
                    customer.isVIP()
            ));
        }

        log.info("Total customers fetched: {}", customerDTOs.size());
        return customerDTOs;
    }

//    @Override
//    public String updateCustomer(int customerID, CustomerDTO customerDTO) {
//        // Fetch the customer by ID
//        Customer customer = customerRepo.findById(customerID).orElse(null);
//
//        if (customer == null) {
//            return "Customer not found!";
//        }
//
//        // Update the customer fields
//        customer.setName(customerDTO.getName());
//        customer.setVIP(customerDTO.isVIP());
//
//
//        // Save the updated customer
//        customerRepo.save(customer);
//
//        return "Customer updated successfully!";
//    }

    @Override
    public String deleteCustomer(int customerID) {
        log.info("Attempting to delete customer with ID: {}", customerID);
        // Check if the customer exists
        if (customerRepo.existsById(customerID)) {
            customerRepo.deleteById(customerID);
            log.info("Customer with ID {} deleted successfully.", customerID);
            return "Customer deleted successfully!";
        } else {
            log.warn("Customer with ID {} not found.", customerID);
            return "Customer not found!";
        }
    }

    @Override
    public String updateCustomer(CustomerDTO customerDTO) {
        log.info("Updating customer with ID: {}", customerDTO.getCustomerId());

        boolean b = customerRepo.existsById(customerDTO.getCustomerId());

        if (b){
            Customer referenceById = customerRepo.getReferenceById(customerDTO.getCustomerId());
            referenceById.setName(customerDTO.getName());
            referenceById.setVIP(customerDTO.isVIP());

            customerRepo.save(referenceById);
            log.info("Customer with ID {} updated successfully.", customerDTO.getCustomerId());
            return "Customer has been updated successfully...";
        }else {
            log.warn("Customer with ID {} not found.", customerDTO.getCustomerId());
            return "Customer not found!";
        }

    }

    @Override
    public CustomerDTO getCustomerById(int customerID) {
        log.info("Fetching customer by ID: {}", customerID);
        if (customerRepo.existsById(customerID)) {
            Customer customer = customerRepo.findById(customerID).get();
            log.info("Customer found: {}", customer.getName());
            return new CustomerDTO(customer.getCustomerId(), customer.getName(), customer.isVIP());
        } else {
            log.warn("Customer with ID {} not found.", customerID);
            return null;
        }
    }



}
