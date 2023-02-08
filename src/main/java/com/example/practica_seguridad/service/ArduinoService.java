package com.example.practica_seguridad.service;

import com.example.practica_seguridad.model.DatosSensor;
import com.example.practica_seguridad.repository.ArduinoRepository;
import com.example.practica_seguridad.repository.IArduinoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ArduinoService implements IArduinoService {
    @Autowired
    private ArduinoRepository arduinoRepository;

    @Override
    public DatosSensor findByName(String name) {
        return arduinoRepository.findOneByTipoSensor(name)
                .orElseThrow(()-> new UsernameNotFoundException("El sensor "+ name+" no existe."));
    }
    @Override
    public DatosSensor create(DatosSensor datosSensor) {
        return arduinoRepository.save(datosSensor);
    }
}
