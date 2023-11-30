package com.example.practica_seguridad.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "depositoAgua")
@Data
@AllArgsConstructor
public class DepositoAgua {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idDeposito")
    private Long idDeposito;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name = "fecha")
    private Date fecha;
    @Column(name = "direccionMAC",length = 250, nullable = false)
    private String direccionMAC;
    @Column(name = "capacidadTanque")
    private double capacidadTanque;
    @Column(name = "alturaTanque")
    private double alturaTanque;
    @Column(name = "nivelAgua")
    private double nivelAgua;
    @Column(name = "porcentaje")
    private double porcentaje;
    @Column(name = "descripcion")
    private String descripcion;
    @ManyToOne
    @JoinColumn(name = "idZona")
    private ZonaRiego zonaRiego;

    public DepositoAgua() {

    }

    public DepositoAgua(Long idDeposito, String descripcion) {
        this.idDeposito = idDeposito;
        this.descripcion = descripcion;
    }
}
