package org.uteq.microserviciosoporte2.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.uteq.microserviciosoporte2.entity.Usuario;
import org.uteq.microserviciosoporte2.service.AuthService;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> datos) {

        Usuario usuario = authService.login(
                datos.get("username"),
                datos.get("password")
        );

        Map<String, Object> respuesta = new HashMap<>();

        if (usuario.getPrimerLogin()) {
            respuesta.put("estado", "CAMBIO_OBLIGATORIO");
        } else {
            respuesta.put("estado", "LOGIN_OK");
        }

        respuesta.put("idUsuario", usuario.getIdUsuario());
        respuesta.put("rol", usuario.getRol().getNombre());

        return ResponseEntity.ok(respuesta);
    }

    @GetMapping("/generar-hash/{password}")
    public String generarHash(@PathVariable String password) {
        return new org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder().encode(password);
    }

    @PostMapping("/cambiar-password")
    public ResponseEntity<?> cambiarCredenciales(@RequestBody Map<String, String> datos) {

        authService.cambiarCredenciales(
                Integer.parseInt(datos.get("idUsuario")),
                datos.get("nuevoUsername"),
                datos.get("nuevaPassword")
        );

        return ResponseEntity.ok("Credenciales actualizadas");
    }
}