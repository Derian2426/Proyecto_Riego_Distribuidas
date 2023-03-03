package com.example.practica_seguridad.service;

import com.example.practica_seguridad.model.Sensor;
import com.example.practica_seguridad.repository.ISensorService;
import com.example.practica_seguridad.repository.SensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SensorService implements ISensorService {
    @Autowired
    private SensorRepository sensorRepository;
    @Override
    public Sensor create(Sensor sensor) {
        return sensorRepository.save(sensor);
    }

    @Override
    public Sensor update(Sensor sensor) {
        return sensorRepository.save(sensor);
    }

    @Override
    public Sensor findById(Integer idSensor) {
        Optional<Sensor> usuario=sensorRepository.findById(idSensor);
        return usuario.orElse(null);
    }

    @Override
    public List<Sensor> findAll() {
        return sensorRepository.findAll();
    }

    @Override
    public void delete(Integer idSensor) {
        sensorRepository.deleteById(idSensor);
    }
}
