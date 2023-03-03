package com.example.practica_seguridad.service;

import com.example.practica_seguridad.model.MonitoreoSuelo;
import com.example.practica_seguridad.model.MonitoreoTemperatura;
import com.example.practica_seguridad.repository.IMonitoreoSueloService;
import com.example.practica_seguridad.repository.IMonitoreoTemperaturaService;
import com.example.practica_seguridad.repository.MonitoreoTemperaturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MonitoreoTemperaturaService implements IMonitoreoTemperaturaService {
    @Autowired
    private MonitoreoTemperaturaRepository monitoreoTemperaturaRepository;

    @Override
    public MonitoreoTemperatura create(MonitoreoTemperatura monitoreoTemperatura) {
        return monitoreoTemperaturaRepository.save(monitoreoTemperatura);
    }

    @Override
    public MonitoreoTemperatura update(MonitoreoTemperatura monitoreoTemperatura) {
        return monitoreoTemperaturaRepository.save(monitoreoTemperatura);
    }

    @Override
    public MonitoreoTemperatura findById(Integer idMonitoreoTemperatura) {
        Optional<MonitoreoTemperatura> monitoreoSuelo=monitoreoTemperaturaRepository.findById(idMonitoreoTemperatura);
        return monitoreoSuelo.orElse(null);
    }

    @Override
    public List<MonitoreoTemperatura> findAll() {
        return monitoreoTemperaturaRepository.findAll();
    }

    @Override
    public void delete(Integer idMonitoreoTemperatura) {
        monitoreoTemperaturaRepository.deleteById(idMonitoreoTemperatura);
    }
}
