package org.ticketsystem.ticeketsystem_be.service;

import org.ticketsystem.ticeketsystem_be.dto.TicketDTO;

import java.util.List;

public interface TicketService {
    String purchesTicket(int ticketID, int customerID);

    List<TicketDTO> getAllTickets();

    List<TicketDTO> getAvailableTickets();
}
