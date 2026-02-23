package org.uteq.microserviciosoporte2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.uteq.microserviciosoporte2.entity.EstadoTicket;

import java.util.Optional;

public interface EstadoTicketRepository extends JpaRepository<EstadoTicket, Integer> {

    Optional<EstadoTicket> findByNombreIgnoreCase(String nombre);
}
