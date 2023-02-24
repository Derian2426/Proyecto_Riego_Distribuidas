package com.example.practica_seguridad.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "detalleSensor")
@Data
public class DetalleSensor {
    @Id
    @Column(name = "idsSensorDetalle")
    private Integer idsSensorDetalle;
    @ManyToOne
    @JoinColumn(name = "idSensor")
    private Sensor sensor;

    @ManyToOne
    @JoinColumn(name = "idZona")
    private ZonaRiego zona;
}
