package com.example.practica_seguridad.repository;

import com.example.practica_seguridad.model.DepositoAgua;
import com.example.practica_seguridad.model.Sensor;

import java.util.List;

public interface IDepositoAgua {
    DepositoAgua create(DepositoAgua depositoAgua);
    DepositoAgua update(DepositoAgua depositoAgua);
    DepositoAgua findById(Integer idDepositoAgua);
    List<DepositoAgua> findAll();
    void delete(Integer idSensor);
}
