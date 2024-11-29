package org.ticketsystem.ticeketsystem_be.Service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl {

    @Autowired
    private CustomerRepository customerRepository;

}
