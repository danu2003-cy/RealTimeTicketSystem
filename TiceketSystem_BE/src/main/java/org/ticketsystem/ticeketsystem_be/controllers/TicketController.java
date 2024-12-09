package org.ticketsystem.ticeketsystem_be.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.ticketsystem.ticeketsystem_be.Util.StandardResponse;
import org.ticketsystem.ticeketsystem_be.dto.TicketDTO;
import org.ticketsystem.ticeketsystem_be.service.TicketService;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("ticket")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @PostMapping(path = "purchase-ticket", params = {"ticketId", "customerId"})
    public ResponseEntity<StandardResponse>saveTicket(
            @RequestParam(name = "ticketId")int ticketID,
            @RequestParam(name = "customerId") int customerID)
    {
        String data = ticketService.purchesTicket(ticketID, customerID);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(201, "Success", data), HttpStatus.CREATED
        );
    }

    @GetMapping(path = "/get-all-tickets")
    public ResponseEntity<StandardResponse> getAllTickets(){

        List<TicketDTO> dataList = ticketService.getAllTickets();
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(201,"Success",dataList),HttpStatus.CREATED
        );
    }

    @GetMapping(path = "/get-available-tickets")
    public ResponseEntity<StandardResponse> getAvailableTickets(){
        List<TicketDTO> dtoList = ticketService.getAvailableTickets();
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(201,"Success",dtoList),HttpStatus.CREATED
        );
    }



}
