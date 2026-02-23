package org.uteq.microserviciosoporte2.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "prioridad_ticket")
public class PrioridadTicket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_prioridad")
    private Integer idPrioridad;

    @Column(name = "nombre", nullable = false, unique = true)
    private String nombre;

    public PrioridadTicket() {
    }

    public Integer getIdPrioridad() {
        return idPrioridad;
    }

    public void setIdPrioridad(Integer idPrioridad) {
        this.idPrioridad = idPrioridad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
