package org.ticketsystem.ticeketsystem_be.service;

import org.ticketsystem.ticeketsystem_be.dto.CustomerDTO;

import java.util.List;

public interface CustomerService {

    String addCustomer(CustomerDTO customerDTO);

    List<CustomerDTO> getAllCustomers();

    String updateCustomer(int customerID, CustomerDTO customerDTO);

    String deleteCustomer(int id);
}
