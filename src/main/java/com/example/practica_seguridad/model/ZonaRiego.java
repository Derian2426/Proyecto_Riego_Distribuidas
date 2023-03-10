package com.example.practica_seguridad.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "zonaRiego")
@Data
public class ZonaRiego {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idZona")
    private Long idZona;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "tamaño")
    private double tamaño;
    @Column(name = "tipoCultivo")
    private String tipoCultivo;
    @Column(name = "tipoRiego")
    private String tipoRiego;
    @Column(name = "ultimaTemaperatura")
    private Float ultimaTemaperatura;
    @Column(name = "ultimaHumedadAmbiente")
    private Float ultimaHumedadAmbiente;
    @Column(name = "ultimaHumedadSuelo")
    private Float ultimaHumedadSuelo;
    @ManyToOne
    @JoinColumn(name = "idSistema")
    private SistemaRiego sistemaRiego;

}
