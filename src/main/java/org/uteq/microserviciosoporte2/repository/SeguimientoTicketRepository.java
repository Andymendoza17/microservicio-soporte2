package org.uteq.microserviciosoporte2.repository;

import org.uteq.microserviciosoporte2.entity.SeguimientoTicket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SeguimientoTicketRepository extends JpaRepository<SeguimientoTicket, Integer> {

    List<SeguimientoTicket> findByTicket_IdTicket(Integer idTicket);
}
