package org.uteq.microserviciosoporte2.client;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ClienteApiClient {

    private final RestTemplate restTemplate;

    public ClienteApiClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public boolean existeCliente(Integer idCliente) {
        try {
            String url = "http://localhost:8081/api/clientes/" + idCliente;
            restTemplate.getForObject(url, Object.class);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public List<Map<String, Object>> obtenerTodosLosClientes() {
        try {
            String url = "http://localhost:8081/api/clientes";
            Map<String, Object>[] clientes = restTemplate.getForObject(url, Map[].class);
            return clientes != null ? Arrays.asList(clientes) : Collections.emptyList();
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }

    public Map<String, Object> obtenerClientePorCedula(String cedula) {
        try {
            String url = "http://localhost:8081/api/clientes/cedula/" + cedula;
            return restTemplate.getForObject(url, Map.class);
        } catch (Exception e) {
            return null;
        }
    }
}
