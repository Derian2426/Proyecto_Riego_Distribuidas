package com.example.practica_seguridad.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

@Entity
@Table(name = "Cosecha")
@Data
public class Cosecha {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCosecha")
    private Long idCosecha;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "tipoCultivo")
    private String tipoCultivo;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name = "fechaSiembra")
    private Date fechaSiembra;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name = "fechaCosecha")
    private Date fechaCosecha;
    @Column(name = "rendimiento")
    private double rendimiento;
    @ManyToOne
    @JoinColumn(name = "idZona")
    private ZonaRiego zonaRiego;
}
