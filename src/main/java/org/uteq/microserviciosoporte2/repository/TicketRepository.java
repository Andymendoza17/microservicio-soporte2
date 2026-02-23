package org.uteq.microserviciosoporte2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.uteq.microserviciosoporte2.entity.Ticket;

import java.util.List;
import java.util.Optional;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {

    Optional<Ticket> findByIdVenta(Integer idVenta);

    List<Ticket> findByIdCliente(Integer idCliente);
}
