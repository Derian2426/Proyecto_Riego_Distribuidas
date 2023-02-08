package com.example.practica_seguridad.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "datosMeteorologicos")
public class DatosMeteorologicos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime fecha;
    private double temperatura;
    private double humedad;
    private double precipitacion;
    private double viento;
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

    public double getHumedad() {
        return humedad;
    }

    public void setHumedad(double humedad) {
        this.humedad = humedad;
    }

    public double getPrecipitacion() {
        return precipitacion;
    }

    public void setPrecipitacion(double precipitacion) {
        this.precipitacion = precipitacion;
    }

    public double getViento() {
        return viento;
    }

    public void setViento(double viento) {
        this.viento = viento;
    }
}
