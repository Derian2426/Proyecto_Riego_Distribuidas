package com.example.practica_seguridad.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
@Entity
@Table(name = "sistemaRiego")
public class SistemaRiego {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String tipo;
    private LocalDateTime fechaInstalacion;
    private String modoRiego;
    private String configuracionRiego;

    @OneToOne(mappedBy = "sistemaRiego")
    private ZonaRiego zonaRiego;
    //getters y setters

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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public LocalDateTime getFechaInstalacion() {
        return fechaInstalacion;
    }

    public void setFechaInstalacion(LocalDateTime fechaInstalacion) {
        this.fechaInstalacion = fechaInstalacion;
    }

    public String getModoRiego() {
        return modoRiego;
    }

    public void setModoRiego(String modoRiego) {
        this.modoRiego = modoRiego;
    }

    public String getConfiguracionRiego() {
        return configuracionRiego;
    }

    public void setConfiguracionRiego(String configuracionRiego) {
        this.configuracionRiego = configuracionRiego;
    }
}
