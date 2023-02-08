package com.example.practica_seguridad.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "cosecha")
public class Cosecha {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String tipoCultivo;
    private LocalDateTime fechaSiembra;
    private LocalDateTime fechaCosecha;
    private double rendimiento;
    @ManyToOne
    private ZonaRiego zonaRiego;
//getters y setters


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipoCultivo() {
        return tipoCultivo;
    }

    public void setTipoCultivo(String tipoCultivo) {
        this.tipoCultivo = tipoCultivo;
    }

    public LocalDateTime getFechaSiembra() {
        return fechaSiembra;
    }

    public void setFechaSiembra(LocalDateTime fechaSiembra) {
        this.fechaSiembra = fechaSiembra;
    }

    public LocalDateTime getFechaCosecha() {
        return fechaCosecha;
    }

    public void setFechaCosecha(LocalDateTime fechaCosecha) {
        this.fechaCosecha = fechaCosecha;
    }

    public double getRendimiento() {
        return rendimiento;
    }

    public void setRendimiento(double rendimiento) {
        this.rendimiento = rendimiento;
    }
}
