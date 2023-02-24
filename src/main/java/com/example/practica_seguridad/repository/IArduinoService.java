package com.example.practica_seguridad.repository;

import com.example.practica_seguridad.model.DatosSensor;

public interface IArduinoService {
    DatosSensor findByName(String name);
    DatosSensor create(DatosSensor datosSensor);
}
