package com.example.practica_seguridad.service;

import com.example.practica_seguridad.model.DetalleSensor;
import com.example.practica_seguridad.repository.DetalleSensorRepository;
import com.example.practica_seguridad.interfaces.IDetalleSensor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DetalleSensorService implements IDetalleSensor {
    @Autowired
    private DetalleSensorRepository detalleSensorRepository;
    @Override
    public DetalleSensor create(DetalleSensor detalleSensor) {
        return detalleSensorRepository.save(detalleSensor);
    }

    @Override
    public List<DetalleSensor> saveAll(List<DetalleSensor> detalleSensors) {
        return detalleSensorRepository.saveAll(detalleSensors);
    }

    @Override
    public DetalleSensor update(DetalleSensor detalleSensor) {
        return detalleSensorRepository.save(detalleSensor);
    }

    @Override
    public DetalleSensor findById(Integer idDetalleSensor) {
        Optional<DetalleSensor> detalleSensor=detalleSensorRepository.findById(idDetalleSensor);
        return detalleSensor.orElse(null);
    }

    @Override
    public List<DetalleSensor> findAll() {
        return detalleSensorRepository.findAll();
    }

    @Override
    public void delete(Integer idDetalleSensor) {
        detalleSensorRepository.deleteById(idDetalleSensor);
    }
}
