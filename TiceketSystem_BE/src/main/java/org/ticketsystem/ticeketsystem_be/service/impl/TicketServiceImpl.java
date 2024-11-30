package org.ticketsystem.ticeketsystem_be.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ticketsystem.ticeketsystem_be.Entity.Customer;
import org.ticketsystem.ticeketsystem_be.Entity.Purchase;
import org.ticketsystem.ticeketsystem_be.Entity.Ticket;
import org.ticketsystem.ticeketsystem_be.dto.TicketDTO;
import org.ticketsystem.ticeketsystem_be.repositories.CustomerRepo;
import org.ticketsystem.ticeketsystem_be.repositories.PurchaseRepo;
import org.ticketsystem.ticeketsystem_be.repositories.TicketRepo;
import org.ticketsystem.ticeketsystem_be.service.TicketService;
import org.ticketsystem.ticeketsystem_be.threads.TicketPool;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class TicketServiceImpl implements TicketService {

    @Autowired
    private TicketRepo ticketRepo;

    @Autowired
    private TicketPool ticketPool;

    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private PurchaseRepo purchaseRepo;

    @Override
    public String purchesTicket(int ticketID, int customerID) {
        try {

            Customer customer = customerRepo.getReferenceById(Integer.valueOf(customerID));

            if(customer==null){
                return "Customer is not found...!";
            }

            Ticket removeTicket = ticketPool.removeTicket(ticketID, customerID);

            if(removeTicket==null){
                System.out.println("Ticket with ID " + ticketID + " not found or already sold.");
                return "Ticket is not found or already sold...!";
            }
            removeTicket.setSold(true);
            ticketRepo.save(removeTicket);

            Purchase purchase = new Purchase();

            purchase.setTicketType(removeTicket.getTicketType().toString());
            purchase.setTicket(removeTicket);
            purchase.setDate(new Date());
            purchase.setCustomer(customer);

            purchaseRepo.save(purchase);

            System.out.println("Ticket " + ticketID + " purchased by customer " + customer.getName());
            return "Ticket purchased successfully by customer : " + customer.getName();


        }catch (Exception  e){
            e.printStackTrace();
        }


        return null;
    }

    @Override
    public List<TicketDTO> getAllTickets() {
        List<Ticket> tickets = ticketRepo.findAll();
        List<TicketDTO> ticketDTOs = new ArrayList<>();

        for (Ticket ticket : tickets) {
            ticketDTOs.add(new TicketDTO(
                    ticket.getTicketId(),
                    ticket.getName(),
                    ticket.getPrice(),
                    ticket.isSold(),
                    ticket.getVendor(),
                    ticket.getTicketType()
            ));
        }
        return ticketDTOs;
    }

    @Override
    public List<TicketDTO> getAvailableTickets() {
        List<Ticket> ticketList = ticketRepo.findAllBySoldEquals(false);
        List<TicketDTO> ticketDTOS = new ArrayList<>();

        for (Ticket ticket : ticketList) {
            ticketDTOS.add(new TicketDTO(
                    ticket.getTicketId(),
                    ticket.getName(),
                    ticket.getPrice(),
                    ticket.isSold(),
                    ticket.getVendor(),
                    ticket.getTicketType()
            ));
        }
        return ticketDTOS;
    }
}
