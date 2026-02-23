package org.uteq.microserviciosoporte2.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.uteq.microserviciosoporte2.client.ClienteApiClient;
import org.uteq.microserviciosoporte2.client.ProductoApiClient;
import org.uteq.microserviciosoporte2.client.VentaApiClient;
import org.uteq.microserviciosoporte2.repository.TicketRepository;

@RestController
public class TestController {

    private final TicketRepository ticketRepository;
    private final ClienteApiClient clienteApiClient;
    private final ProductoApiClient productoApiClient;
    private final VentaApiClient ventaApiClient;

    public TestController(
            TicketRepository ticketRepository,
            ClienteApiClient clienteApiClient,
            ProductoApiClient productoApiClient,
            VentaApiClient ventaApiClient) {
        this.ticketRepository = ticketRepository;
        this.clienteApiClient = clienteApiClient;
        this.productoApiClient = productoApiClient;
        this.ventaApiClient = ventaApiClient;
    }

    @GetMapping("/api/test")
    public String test() {
        return "Microservicio funcionando OK";
    }

    @GetMapping("/api/debug")
    public Map<String, Object> debug() {
        Map<String, Object> debug = new HashMap<>();
        debug.put("status", "OK");
        debug.put("timestamp", System.currentTimeMillis());
        debug.put("totalTickets", ticketRepository.count());
        return debug;
    }

    @GetMapping("/api/clientes")
    public List<Map<String, Object>> obtenerClientes() {
        return clienteApiClient.obtenerTodosLosClientes();
    }

    @GetMapping("/api/productos")
    public List<Map<String, Object>> obtenerProductos() {
        List<Map<String, Object>> productos = new ArrayList<>();
        try {
            // Simulación si no está disponible
            return productos;
        } catch (Exception e) {
            return productos;
        }
    }

    @GetMapping("/api/ventas")
    public List<Map<String, Object>> obtenerVentas() {
        List<Map<String, Object>> ventas = new ArrayList<>();
        try {
            // Simulación si no está disponible
            return ventas;
        } catch (Exception e) {
            return ventas;
        }
    }
}
