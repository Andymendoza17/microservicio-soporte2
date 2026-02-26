package org.uteq.microserviciosoporte2.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.uteq.microserviciosoporte2.entity.Usuario;
import org.uteq.microserviciosoporte2.repository.UsuarioRepository;

@Service
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public AuthService(UsuarioRepository usuarioRepository,
                       BCryptPasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // LOGIN
    public Usuario login(String username, String password) {

        Usuario usuario = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (!passwordEncoder.matches(password, usuario.getPasswordHash())) {
            throw new RuntimeException("Contraseña incorrecta");
        }

        return usuario;
    }

    // CAMBIAR USUARIO Y CONTRASEÑA
    public void cambiarCredenciales(Integer idUsuario,
                                    String nuevoUsername,
                                    String nuevaPassword) {

        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        usuario.setUsername(nuevoUsername);
        usuario.setPasswordHash(passwordEncoder.encode(nuevaPassword));
        usuario.setPrimerLogin(false);

        usuarioRepository.save(usuario);
    }
}