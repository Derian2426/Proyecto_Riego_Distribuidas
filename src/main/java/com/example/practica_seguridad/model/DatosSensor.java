package com.example.practica_seguridad.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
public class DatosSensor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date fecha;
    private String tipoSensor;
    private double lectura;

//getters y setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
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
