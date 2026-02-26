package org.uteq.microserviciosoporte2.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Integer idUsuario;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(name = "password_hash", nullable = false)
    private String passwordHash;

    @ManyToOne
    @JoinColumn(name = "id_rol", nullable = false)
    private Rol rol;

    @Column(name = "id_externo", nullable = false)
    private Long idExterno;

    @Column(name = "primer_login")
    private Boolean primerLogin = true;

    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;

    // ===== GETTERS =====

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public String getUsername() {
        return username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public Rol getRol() {
        return rol;
    }

    public Long getIdExterno() {
        return idExterno;
    }

    public Boolean getPrimerLogin() {
        return primerLogin;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    // ===== SETTERS =====

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public void setIdExterno(Long idExterno) {
        this.idExterno = idExterno;
    }

    public void setPrimerLogin(Boolean primerLogin) {
        this.primerLogin = primerLogin;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
}