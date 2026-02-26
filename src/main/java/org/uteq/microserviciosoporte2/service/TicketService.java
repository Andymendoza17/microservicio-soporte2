package org.uteq.microserviciosoporte2.service;

import org.springframework.stereotype.Service;
import org.uteq.microserviciosoporte2.entity.EstadoTicket;
import org.uteq.microserviciosoporte2.entity.Ticket;
import org.uteq.microserviciosoporte2.entity.Usuario;
import org.uteq.microserviciosoporte2.repository.EstadoTicketRepository;
import org.uteq.microserviciosoporte2.repository.TicketRepository;
import org.uteq.microserviciosoporte2.repository.UsuarioRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;
    private final EstadoTicketRepository estadoRepository;
    private final UsuarioRepository usuarioRepository;

    public TicketService(TicketRepository ticketRepository,
                         EstadoTicketRepository estadoRepository,
                         UsuarioRepository usuarioRepository) {
        this.ticketRepository = ticketRepository;
        this.estadoRepository = estadoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    // =========================
    // CREAR TICKET DESDE USUARIO LOGUEADO
    // =========================
    public Ticket crearDesdeUsuario(Integer idUsuario, Ticket ticket) {

        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        EstadoTicket estadoInicial = estadoRepository.findById(1)
                .orElseThrow(() -> new RuntimeException("Estado inicial no existe"));

        ticket.setUsuarioCreador(usuario);
        ticket.setEstado(estadoInicial);
        ticket.setFechaCreacion(LocalDateTime.now());
        ticket.setFechaCierre(null);

        return ticketRepository.save(ticket);
    }

    // =========================
    // LISTAR TICKETS POR USUARIO
    // =========================
    public List<Ticket> listarPorUsuario(Integer idUsuario) {
        return ticketRepository.findByUsuarioCreador_IdUsuario(idUsuario);
    }

    // =========================
    // LISTAR TODOS (ADMIN)
    // =========================
    public List<Ticket> listarTickets() {
        return ticketRepository.findAll();
    }

    // =========================
    // OBTENER POR ID
    // =========================
    public Optional<Ticket> obtenerTicketPorId(Integer id) {
        return ticketRepository.findById(id);
    }
}