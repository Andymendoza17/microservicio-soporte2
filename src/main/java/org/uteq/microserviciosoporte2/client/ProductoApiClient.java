package org.uteq.microserviciosoporte2.client;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ProductoApiClient {

    private final RestTemplate restTemplate;

    public ProductoApiClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public boolean existeProducto(Integer idProducto) {
        try {
            String url = "http://localhost:8082/api/productos/" + idProducto;
            restTemplate.getForObject(url, Object.class);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
