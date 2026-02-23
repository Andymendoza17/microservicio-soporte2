package org.uteq.microserviciosoporte2.client;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class VentaApiClient {

    private final RestTemplate restTemplate;

    public VentaApiClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public boolean existeVenta(Integer idVenta) {
        try {
            String url = "http://localhost:8083/api/ventas/" + idVenta;
            restTemplate.getForObject(url, Object.class);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
