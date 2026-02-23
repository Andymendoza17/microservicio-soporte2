package org.uteq.microserviciosoporte2.service;

import org.springframework.stereotype.Service;
import org.uteq.microserviciosoporte2.client.ClienteApiClient;
import org.uteq.microserviciosoporte2.client.ProductoApiClient;
import org.uteq.microserviciosoporte2.client.VentaApiClient;
import org.uteq.microserviciosoporte2.entity.EstadoTicket;
import org.uteq.microserviciosoporte2.entity.Ticket;
import org.uteq.microserviciosoporte2.repository.EstadoTicketRepository;
import org.uteq.microserviciosoporte2.repository.TicketRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;
    private final EstadoTicketRepository estadoTicketRepository;

    private final ClienteApiClient clienteApiClient;
    private final ProductoApiClient productoApiClient;
    private final VentaApiClient ventaApiClient;

    public TicketService(
            TicketRepository ticketRepository,
            EstadoTicketRepository estadoTicketRepository,
            ClienteApiClient clienteApiClient,
            ProductoApiClient productoApiClient,
            VentaApiClient ventaApiClient
    ) {
        this.ticketRepository = ticketRepository;
        this.estadoTicketRepository = estadoTicketRepository;
        this.clienteApiClient = clienteApiClient;
        this.productoApiClient = productoApiClient;
        this.ventaApiClient = ventaApiClient;
    }

    // ===== CREAR TICKET =====
        public Ticket crearTicket(Ticket ticket) {

        // Validar cliente externo
//        if (!clienteApiClient.existeCliente(ticket.getIdCliente())) {
//            throw new RuntimeException("Cliente no existe en sistema externo");
//        }
//
//        // Validar producto externo
//        if (!productoApiClient.existeProducto(ticket.getIdProducto())) {
//            throw new RuntimeException("Producto no existe en sistema externo");
//        }
//
//        // Validar venta externa
//        if (!ventaApiClient.existeVenta(ticket.getIdVenta())) {
//            throw new RuntimeException("Venta no existe en sistema externo");
//        }

        EstadoTicket estado = estadoTicketRepository
                .findById(1)
                .orElseThrow(() -> new RuntimeException("Estado inicial no existe"));

        ticket.setEstado(estado);
        ticket.setFechaCierre(null);
        ticket.setFechaCreacion(LocalDateTime.now());

        return ticketRepository.save(ticket);
    }

    // ===== LISTAR TODOS =====
    public List<Ticket> listarTickets() {
        return ticketRepository.findAll();
    }

    // ===== BUSCAR POR ID =====
    public Optional<Ticket> obtenerTicketPorId(Integer id) {
        return ticketRepository.findById(id);
    }

    // ===== ACTUALIZAR =====
    public Ticket actualizarTicket(Integer id, Ticket ticketActualizado) {

        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ticket no encontrado"));

        ticket.setDescripcion(ticketActualizado.getDescripcion());
        ticket.setPrioridad(ticketActualizado.getPrioridad());
        ticket.setTipo(ticketActualizado.getTipo());

        return ticketRepository.save(ticket);
    }

    // ===== CERRAR TICKET =====
    public Ticket cerrarTicket(Integer id) {

        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ticket no encontrado"));

        EstadoTicket estadoCerrado = estadoTicketRepository
                .findById(5)
                .orElseThrow(() -> new RuntimeException("Estado CERRADO no existe"));

        ticket.setEstado(estadoCerrado);
        ticket.setFechaCierre(LocalDateTime.now());

        return ticketRepository.save(ticket);
    }

    // ===== ELIMINAR =====
    public void eliminarTicket(Integer id) {
        ticketRepository.deleteById(id);
    }

    // ===== BUSCAR POR CLIENTE =====
    public List<Ticket> listarPorCliente(Integer idCliente) {
        return ticketRepository.findByIdCliente(idCliente);
    }

    // ===== BUSCAR POR VENTA =====
    public Optional<Ticket> buscarPorVenta(Integer idVenta) {
        return ticketRepository.findByIdVenta(idVenta);
    }
}
