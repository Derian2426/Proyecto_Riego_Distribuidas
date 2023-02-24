package com.example.practica_seguridad.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "Sensor")
@Data
public class Sensor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idSensor")
    private Long idSensor;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "ubicacion")
    private String ubicacion;
    @Column(name = "fechaInstalacion")
    private LocalDateTime fechaInstalacion;
    @Column(name = "ultimaMedicion")
    private double ultimaMedicion;
}
