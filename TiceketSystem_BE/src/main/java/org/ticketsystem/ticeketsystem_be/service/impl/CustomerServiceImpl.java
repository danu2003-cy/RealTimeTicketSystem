package org.ticketsystem.ticeketsystem_be.service.impl;

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

        Customer customer = new Customer();
        customer.setName(customerDTO.getName());
        customer.setRetrievalInterval(configurationDTO.getCustomerRetrievalRate());
        customer.setVIP(customer.isVIP());

        customerRepo.save(customer);
        return "Customer has been created successfully...!";
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        List<Customer> customers = customerRepo.findAll();
        List<CustomerDTO> customerDTOs = new ArrayList<>();
        for (Customer customer : customers) {
            customerDTOs.add(new CustomerDTO(
                    customer.getCustomerId(),
                    customer.getName(),
                    customer.isVIP()
            ));
        }
        return customerDTOs;
    }

    @Override
    public String updateCustomer(int customerID, CustomerDTO customerDTO) {
        return "";
    }

    @Override
    public String deleteCustomer(int customerID){
        return null;
    }
}
