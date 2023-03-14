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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    @Column(name = "fechaMedicion")
    private Date fechaMedicion;
    @Column(name = "humedad")
    private double humedad;
    @Column(name = "nitrogeno")
    private double nitrogeno;
    @Column(name = "fosforo")
    private double fosforo;
    @Column(name = "potasio")
    private double potasio;
    @Column(name = "unidadMedida")
    private String unidadMedida;
    @ManyToOne
    @JoinColumn(name = "idZona")
    private ZonaRiego zonaRiego;
}
