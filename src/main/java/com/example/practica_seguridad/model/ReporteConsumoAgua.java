package com.example.practica_seguridad.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
@Entity
@Table(name = "reporteConsumoAgua")
public class ReporteConsumoAgua {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime fecha;
    private double cantidadAgua;
    @ManyToOne
    private ZonaRiego zonaRiego;
//getters y setters


}
