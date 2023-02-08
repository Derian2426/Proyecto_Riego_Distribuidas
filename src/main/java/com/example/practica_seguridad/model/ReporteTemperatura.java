package com.example.practica_seguridad.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class ReporteTemperatura {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime fecha;
    private double temperatura;
    @ManyToOne
    private ZonaRiego zonaRiego;
//getters y setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public double getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(double temperatura) {
        this.temperatura = temperatura;
    }
}
