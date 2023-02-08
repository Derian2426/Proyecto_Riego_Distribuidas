package com.example.practica_seguridad.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class ControlRiego {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime fecha;
    private double tiempoRiego;
    private double cantidadAgua;
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

    public double getTiempoRiego() {
        return tiempoRiego;
    }

    public void setTiempoRiego(double tiempoRiego) {
        this.tiempoRiego = tiempoRiego;
    }

    public double getCantidadAgua() {
        return cantidadAgua;
    }

    public void setCantidadAgua(double cantidadAgua) {
        this.cantidadAgua = cantidadAgua;
    }
}
