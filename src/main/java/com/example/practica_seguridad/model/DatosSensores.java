package com.example.practica_seguridad.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class DatosSensores {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime fecha;
    private String tipoSensor;
    private double lectura;
    @ManyToOne
    private SensorHumedad sensorHumedad;
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

    public String getTipoSensor() {
        return tipoSensor;
    }

    public void setTipoSensor(String tipoSensor) {
        this.tipoSensor = tipoSensor;
    }

    public double getLectura() {
        return lectura;
    }

    public void setLectura(double lectura) {
        this.lectura = lectura;
    }
}
