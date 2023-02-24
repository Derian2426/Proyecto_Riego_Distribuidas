package com.example.practica_seguridad.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "monitoreoTemperatura")
@Data
public class MonitoreoTemperatura {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idTemperatura")
    private Long idTemperatura;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name = "fechaMedicion", nullable = false)
    private Date fechaMedicion;
    @Column(name = "temperatura")
    private double temperatura;
    @Column(name = "humedad")
    private double humedad;
    @ManyToOne
    @JoinColumn(name = "idZona")
    private ZonaRiego zonaRiego;
}
