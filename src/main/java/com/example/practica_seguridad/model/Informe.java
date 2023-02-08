package com.example.practica_seguridad.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity

public class Informe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime fecha;
    private String tipo;
    private String detalles;
    @ManyToOne
    private Usuario usuario;
//getters y setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDetalles() {
        return detalles;
    }

    public void setDetalles(String detalles) {
        this.detalles = detalles;
    }
}
