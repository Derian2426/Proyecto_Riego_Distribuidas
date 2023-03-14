package com.example.practica_seguridad.model;

import lombok.Data;

import java.util.List;
@Data
public class DatosSensoresZonas {
    private List<Sensor> sensores;
    private List<ZonaRiego> zonas;
}
