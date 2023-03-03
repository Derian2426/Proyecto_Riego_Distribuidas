package com.example.practica_seguridad.repository;

import com.example.practica_seguridad.model.MonitoreoTemperatura;
import com.example.practica_seguridad.model.Usuario;

import java.util.List;

public interface IMonitoreoTemperaturaService {
    MonitoreoTemperatura create(MonitoreoTemperatura monitoreoTemperatura);
    MonitoreoTemperatura update(MonitoreoTemperatura monitoreoTemperatura);
    MonitoreoTemperatura findById(Integer idMonitoreoTemperatura);
    List<MonitoreoTemperatura> findAll();
    void delete(Integer idMonitoreoTemperatura);
}
