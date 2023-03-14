package com.example.practica_seguridad.repository;

import com.example.practica_seguridad.model.InformeConsumo;
import com.example.practica_seguridad.model.MonitoreoSuelo;

import java.util.List;

public interface IMonitoreoSueloService {
    MonitoreoSuelo create(MonitoreoSuelo monitoreoSuelo);
    MonitoreoSuelo update(MonitoreoSuelo monitoreoSuelo);
    MonitoreoSuelo findById(Integer idMonitoreoSuelo);
    List<MonitoreoSuelo> findAll();
    List<MonitoreoSuelo> findByIdZona(Integer idZona);
    void delete(Integer idMonitoreoSuelo);
}
