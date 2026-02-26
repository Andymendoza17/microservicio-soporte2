package org.uteq.microserviciosoporte2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.uteq.microserviciosoporte2.entity.Ticket;

import java.util.List;
import java.util.Optional;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {

    // Buscar por venta externa (si lo necesitas)
    Optional<Ticket> findByIdVentaExterna(Long idVentaExterna);

    // Listar tickets creados por un usuario
    List<Ticket> findByUsuarioCreador_IdUsuario(Integer idUsuario);
}