package com.example.practica_seguridad.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "zonaRiego")
public class ZonaRiego {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private double tamaño;
    private String tipoCultivo;

    @OneToOne
    private SistemaRiego sistemaRiego;

    @OneToMany(mappedBy = "zonaRiego")
    private List<SensorHumedad> sensoresHumedad;

    @OneToMany(mappedBy = "zonaRiego")
    private List<DatosMeteorologicos> datosMeteorologicos;

    @OneToMany(mappedBy = "zonaRiego")
    private List<Cosecha> cosechas;

    @OneToMany(mappedBy = "zonaRiego")
    private List<ReporteConsumoAgua> reportesConsumoAgua;

    @ManyToOne
    private Usuario usuario;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getTamaño() {
        return tamaño;
    }

    public void setTamaño(double tamaño) {
        this.tamaño = tamaño;
    }

    public String getTipoCultivo() {
        return tipoCultivo;
    }

    public void setTipoCultivo(String tipoCultivo) {
        this.tipoCultivo = tipoCultivo;
    }

    public SistemaRiego getSistemaRiego() {
        return sistemaRiego;
    }

    public void setSistemaRiego(SistemaRiego sistemaRiego) {
        this.sistemaRiego = sistemaRiego;
    }
}
