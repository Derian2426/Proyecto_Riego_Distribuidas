package com.example.practica_seguridad.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Data
@Table(name = "Notificacion")
public class Notificacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idNotificacion")
    private Long idNotificacion;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date fecha;
    private String mensaje;
    private boolean visto;
    @ManyToOne
    @JoinColumn(name = "idUsuario")
    private Usuario usuario;
}
