package com.example.practica_seguridad.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "sensorHumedad")
public class SensorHumedad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String ubicacion;
    private LocalDateTime fechaInstalacion;
    private double ultimaMedicion;

    @OneToMany(mappedBy = "sensorHumedad")
    private List<DatosSensores> datosSensores;

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

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public LocalDateTime getFechaInstalacion() {
        return fechaInstalacion;
    }

    public void setFechaInstalacion(LocalDateTime fechaInstalacion) {
        this.fechaInstalacion = fechaInstalacion;
    }

    public double getUltimaMedicion() {
        return ultimaMedicion;
    }

    public void setUltimaMedicion(double ultimaMedicion) {
        this.ultimaMedicion = ultimaMedicion;
    }
}
