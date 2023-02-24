package com.example.practica_seguridad.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "Notificacion")
public class Notificacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idNotificacion")
    private Long idNotificacion;
    private LocalDateTime fecha;
    private String mensaje;
    private boolean visto;
    @ManyToOne
    @JoinColumn(name = "idUsuario")
    private Usuario usuario;
}
