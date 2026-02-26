package org.uteq.microserviciosoporte2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.uteq.microserviciosoporte2.entity.Usuario;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    Optional<Usuario> findByUsername(String username);
}