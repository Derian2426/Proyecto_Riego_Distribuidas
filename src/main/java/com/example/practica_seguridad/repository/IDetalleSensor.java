package com.example.practica_seguridad.repository;

import com.example.practica_seguridad.model.DetalleSensor;

import java.util.List;

public interface IDetalleSensor {
    DetalleSensor create(DetalleSensor detalleSensor);
    List<DetalleSensor> saveAll(List<DetalleSensor> detalleSensors);
    DetalleSensor update(DetalleSensor detalleSensor);
    DetalleSensor findById(Integer idDetalleSensor);
    List<DetalleSensor> findAll();
    void delete(Integer idDetalleSensor);
}
