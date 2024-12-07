package org.ticketsystem.ticeketsystem_be.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.ticketsystem.ticeketsystem_be.Util.StandardResponse;
import org.ticketsystem.ticeketsystem_be.dto.CustomerDTO;
import org.ticketsystem.ticeketsystem_be.service.CustomerService;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping(path = "add-customer")
    public ResponseEntity<StandardResponse> addCustomer(@RequestBody CustomerDTO customerDTO) {
        String data = customerService.addCustomer(customerDTO);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(201,"success",customerDTO), HttpStatus.CREATED);
    }

    @GetMapping(path = "/get-all-customer")
    public ResponseEntity<StandardResponse> getAllCustomer(){
        List<CustomerDTO> customerList = customerService.getAllCustomers();
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(201,"Success",customerList),HttpStatus.CREATED
        );
    }

    @GetMapping("/get-customer-by-id")
    public ResponseEntity<StandardResponse> getCustomerById(@RequestParam(name = "customerID") int customerID) {
        CustomerDTO customer = customerService.getCustomerById(customerID);
        if (customer != null) {
            return new ResponseEntity<>(
                    new StandardResponse(200, "Success", customer),
                    HttpStatus.OK
            );
        } else {
            return new ResponseEntity<>(
                    new StandardResponse(404, "Customer not found", null),
                    HttpStatus.NOT_FOUND
            );
        }
    }


//    @PutMapping(path = "/update-customer",params = {"customerID"})
//    public ResponseEntity<StandardResponse> updateCustomer(
//            @RequestBody CustomerDTO customerDTO,@RequestParam(name = "customerID") int customerID
//    ){
//        String data = customerService.updateCustomer(customerID,customerDTO);
//        return new ResponseEntity<StandardResponse>(
//                new StandardResponse(201,"Success",data),HttpStatus.CREATED
//        );
//    }

    @PutMapping("/update-customer")
    public ResponseEntity<StandardResponse> updateCustomer(@RequestBody CustomerDTO customerDTO) {

        String message = customerService.updateCustomer(customerDTO);

        return new ResponseEntity<StandardResponse>(
                new StandardResponse(201,"Success",message),HttpStatus.CREATED
        );

    }

    @DeleteMapping(path = "/delete-customer",params = {"customerID"})
    public ResponseEntity<StandardResponse> deleteCustomer(@RequestParam(name = "customerID") int customerID){
        String data= customerService.deleteCustomer(customerID);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(201,"Success",data),HttpStatus.CREATED
        );
    }
}
