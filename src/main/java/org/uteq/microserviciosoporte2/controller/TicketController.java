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

    // ===== CREAR =====
    @PostMapping
    public ResponseEntity<Ticket> crear(@RequestBody Ticket ticket) {
        return ResponseEntity.ok(ticketService.crearTicket(ticket));
    }

    // ===== LISTAR =====
    @GetMapping
    public ResponseEntity<List<Ticket>> listar() {
        return ResponseEntity.ok(ticketService.listarTickets());
    }

    // ===== BUSCAR POR ID =====
    @GetMapping("/{id}")
    public ResponseEntity<Ticket> obtenerPorId(@PathVariable Integer id) {
        return ticketService.obtenerTicketPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ===== ACTUALIZAR =====
    @PutMapping("/{id}")
    public ResponseEntity<Ticket> actualizar(
            @PathVariable Integer id,
            @RequestBody Ticket ticket) {

        return ResponseEntity.ok(ticketService.actualizarTicket(id, ticket));
    }

    // ===== CERRAR =====
    @PutMapping("/{id}/cerrar")
    public ResponseEntity<Ticket> cerrar(@PathVariable Integer id) {
        return ResponseEntity.ok(ticketService.cerrarTicket(id));
    }

    // ===== ELIMINAR =====
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        ticketService.eliminarTicket(id);
        return ResponseEntity.noContent().build();
    }

    // ===== BUSCAR POR CLIENTE =====
    @GetMapping("/cliente/{idCliente}")
    public ResponseEntity<List<Ticket>> listarPorCliente(@PathVariable Integer idCliente) {
        return ResponseEntity.ok(ticketService.listarPorCliente(idCliente));
    }

    // ===== BUSCAR POR VENTA =====
    @GetMapping("/venta/{idVenta}")
    public ResponseEntity<Ticket> buscarPorVenta(@PathVariable Integer idVenta) {
        return ticketService.buscarPorVenta(idVenta)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
