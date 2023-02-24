package com.example.practica_seguridad.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "monitoreoSuelo")
@Data
public class MonitoreoSuelo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idSuelo")
    private Long idSuelo;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name = "fechaMedicion")
    private Date fechaMedicion;
    @Column(name = "humedad")
    private double humedad;
    @Column(name = "pH")
    private double pH;
    @Column(name = "nutrientes")
    private String nutrientes;
    @ManyToOne
    @JoinColumn(name = "idZona")
    private ZonaRiego zonaRiego;
}
