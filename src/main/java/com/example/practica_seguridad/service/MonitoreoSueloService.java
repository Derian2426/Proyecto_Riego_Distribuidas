package com.example.practica_seguridad.service;

import com.example.practica_seguridad.model.MonitoreoSuelo;
import com.example.practica_seguridad.model.ZonaRiego;
import com.example.practica_seguridad.interfaces.IMonitoreoSueloService;
import com.example.practica_seguridad.repository.MonitoreoSueloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class MonitoreoSueloService implements IMonitoreoSueloService {
    @Autowired
    private MonitoreoSueloRepository monitoreoSueloRepository;

    @Override
    @Transactional
    public MonitoreoSuelo create(MonitoreoSuelo monitoreoSuelo) {
        try {
            monitoreoSuelo.setFechaMedicion(new Date());
            return monitoreoSueloRepository.save(monitoreoSuelo);
        } catch (Exception e) {
            return new MonitoreoSuelo(-1L, e.getMessage());
        }
    }

    @Override
    @Transactional
    public MonitoreoSuelo update(MonitoreoSuelo monitoreoSuelo) {
        try {
            return monitoreoSueloRepository.save(monitoreoSuelo);
        } catch (Exception e) {
            return new MonitoreoSuelo(-1L, e.getMessage());
        }
    }

    @Override
    @Transactional
    public MonitoreoSuelo findById(Integer idMonitoreoSuelo) {
        try {
            Optional<MonitoreoSuelo> monitoreoSuelo = monitoreoSueloRepository.findById(idMonitoreoSuelo);
            return monitoreoSuelo.orElse(null);
        } catch (Exception e) {
            return new MonitoreoSuelo(-1L, e.getMessage());
        }
    }

    @Override
    @Transactional
    public List<MonitoreoSuelo> findAll() {
        try {
            return monitoreoSueloRepository.findAll();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    @Override
    @Transactional
    public List<MonitoreoSuelo> findAllSuelo(ZonaRiego zonaRiego) {
        try {
            return monitoreoSueloRepository.findByZonaRiego(zonaRiego)
                    .orElseThrow(() -> new IllegalArgumentException("No ambient temperature log available."));
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    @Override
    @Transactional
    public void delete(Integer idMonitoreoSuelo) {
        try {
            monitoreoSueloRepository.deleteById(idMonitoreoSuelo);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    @Transactional
    public List<MonitoreoSuelo> findByFecha(ZonaRiego zonaRiego, Date fecha) {
        try {
            return monitoreoSueloRepository.findByZonaRiegoAndFechaMedicion(zonaRiego, fecha);
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }
}
