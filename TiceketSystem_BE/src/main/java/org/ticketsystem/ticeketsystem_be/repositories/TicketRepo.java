package org.ticketsystem.ticeketsystem_be.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import org.ticketsystem.ticeketsystem_be.Entity.Ticket;

import java.util.List;

@Repository
@EnableJpaRepositories
public interface TicketRepo extends JpaRepository<Ticket, Integer> {

    List<Ticket> findAllBySoldEquals(boolean b);

    List<Ticket> findBySoldFalse();
}
