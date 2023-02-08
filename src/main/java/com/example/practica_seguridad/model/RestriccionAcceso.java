package com.example.practica_seguridad.model;

import jakarta.persistence.*;

@Entity
@Table(name = "restriccionAcceso")
public class RestriccionAcceso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombreModulo;
    private String tipoUsuario;
    private boolean acceso;
    @ManyToOne
    @JoinColumn(name = "idUsuario")
    private Usuario usuario;
//getters y setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreModulo() {
        return nombreModulo;
    }

    public void setNombreModulo(String nombreModulo) {
        this.nombreModulo = nombreModulo;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public boolean isAcceso() {
        return acceso;
    }

    public void setAcceso(boolean acceso) {
        this.acceso = acceso;
    }
}
