package org.uteq.microserviciosoporte2.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.uteq.microserviciosoporte2.entity.Ticket;
import org.uteq.microserviciosoporte2.service.TicketService;

import java.util.List;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    // =========================
    // CREAR TICKET DESDE USUARIO LOGUEADO
    // =========================
    @PostMapping("/usuario/{idUsuario}")
    public ResponseEntity<Ticket> crearDesdeUsuario(
            @PathVariable Integer idUsuario,
            @RequestBody Ticket ticket) {

        return ResponseEntity.ok(
                ticketService.crearDesdeUsuario(idUsuario, ticket)
        );
    }

    // =========================
    // LISTAR TICKETS POR USUARIO
    // =========================
    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<List<Ticket>> listarPorUsuario(
            @PathVariable Integer idUsuario) {

        return ResponseEntity.ok(
                ticketService.listarPorUsuario(idUsuario)
        );
    }

    // =========================
    // LISTAR TODOS (ADMIN)
    // =========================
    @GetMapping
    public ResponseEntity<List<Ticket>> listarTodos() {
        return ResponseEntity.ok(ticketService.listarTickets());
    }
}