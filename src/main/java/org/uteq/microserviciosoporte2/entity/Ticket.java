package org.uteq.microserviciosoporte2.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "ticket")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ticket")
    private Integer idTicket;

    @ManyToOne
    @JoinColumn(name = "id_usuario_creador", nullable = false)
    private Usuario usuarioCreador;

    @Column(name = "id_cliente_externo", nullable = false)
    private Long idClienteExterno;

    @Column(name = "id_producto_externo", nullable = false)
    private Long idProductoExterno;

    @Column(name = "id_venta_externa")
    private Long idVentaExterna;

    @Column(nullable = false)
    private String descripcion;

    @Column(name = "ruta_archivo")
    private String rutaArchivo;

    @ManyToOne
    @JoinColumn(name = "id_estado", nullable = false)
    private EstadoTicket estado;

    @ManyToOne
    @JoinColumn(name = "id_prioridad", nullable = false)
    private PrioridadTicket prioridad;

    @ManyToOne
    @JoinColumn(name = "id_tipo", nullable = false)
    private TipoIncidente tipo;

    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;

    @Column(name = "fecha_cierre")
    private LocalDateTime fechaCierre;

    // =========================
    // GETTERS
    // =========================

    public Integer getIdTicket() {
        return idTicket;
    }

    public Usuario getUsuarioCreador() {
        return usuarioCreador;
    }

    public Long getIdClienteExterno() {
        return idClienteExterno;
    }

    public Long getIdProductoExterno() {
        return idProductoExterno;
    }

    public Long getIdVentaExterna() {
        return idVentaExterna;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getRutaArchivo() {
        return rutaArchivo;
    }

    public EstadoTicket getEstado() {
        return estado;
    }

    public PrioridadTicket getPrioridad() {
        return prioridad;
    }

    public TipoIncidente getTipo() {
        return tipo;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public LocalDateTime getFechaCierre() {
        return fechaCierre;
    }

    // =========================
    // SETTERS
    // =========================

    public void setIdTicket(Integer idTicket) {
        this.idTicket = idTicket;
    }

    public void setUsuarioCreador(Usuario usuarioCreador) {
        this.usuarioCreador = usuarioCreador;
    }

    public void setIdClienteExterno(Long idClienteExterno) {
        this.idClienteExterno = idClienteExterno;
    }

    public void setIdProductoExterno(Long idProductoExterno) {
        this.idProductoExterno = idProductoExterno;
    }

    public void setIdVentaExterna(Long idVentaExterna) {
        this.idVentaExterna = idVentaExterna;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setRutaArchivo(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
    }

    public void setEstado(EstadoTicket estado) {
        this.estado = estado;
    }

    public void setPrioridad(PrioridadTicket prioridad) {
        this.prioridad = prioridad;
    }

    public void setTipo(TipoIncidente tipo) {
        this.tipo = tipo;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public void setFechaCierre(LocalDateTime fechaCierre) {
        this.fechaCierre = fechaCierre;
    }
}