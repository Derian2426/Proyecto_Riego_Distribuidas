package com.example.practica_seguridad.service;

import com.example.practica_seguridad.model.MonitoreoSuelo;
import com.example.practica_seguridad.repository.IMonitoreoSueloService;
import com.example.practica_seguridad.repository.MonitoreoSueloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MonitoreoSueloService implements IMonitoreoSueloService {
    @Autowired
    private MonitoreoSueloRepository monitoreoSueloRepository;
    @Override
    public MonitoreoSuelo create(MonitoreoSuelo monitoreoSuelo) {
        return monitoreoSueloRepository.save(monitoreoSuelo);
    }

    @Override
    public MonitoreoSuelo update(MonitoreoSuelo monitoreoSuelo) {
        return monitoreoSueloRepository.save(monitoreoSuelo);
    }

    @Override
    public MonitoreoSuelo findById(Integer idMonitoreoSuelo) {
        Optional<MonitoreoSuelo> monitoreoSuelo=monitoreoSueloRepository.findById(idMonitoreoSuelo);
        return monitoreoSuelo.orElse(null);
    }

    @Override
    public List<MonitoreoSuelo> findAll() {
        return monitoreoSueloRepository.findAll();
    }

    @Override
    public List<MonitoreoSuelo> findByIdZona(Integer idZona) {
        return null;
    }

    @Override
    public void delete(Integer idMonitoreoSuelo) {
        monitoreoSueloRepository.deleteById(idMonitoreoSuelo);
    }
}
