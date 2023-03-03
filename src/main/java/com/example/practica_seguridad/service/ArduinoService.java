package com.example.practica_seguridad.service;

import com.example.practica_seguridad.model.SistemaRiego;
import com.example.practica_seguridad.model.Usuario;
import com.example.practica_seguridad.repository.ArduinoRepository;
import com.example.practica_seguridad.repository.IArduinoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ArduinoService implements IArduinoService {
    @Autowired
    private ArduinoRepository arduinoRepository;

    @Override
    public SistemaRiego findByName(String name) {
        return arduinoRepository.findOneByNombre(name)
                .orElseThrow(()-> new UsernameNotFoundException("El sistema "+ name+" no existe."));
    }
    @Override
    public SistemaRiego create(SistemaRiego sistemaRiego) {
        return arduinoRepository.save(sistemaRiego);
    }

    @Override
    public SistemaRiego update(SistemaRiego sistemaRiego) {
        return arduinoRepository.save(sistemaRiego);
    }

    @Override
    public SistemaRiego findById(Integer idSistema) {
        Optional<SistemaRiego> usuario=arduinoRepository.findById(idSistema);
        return usuario.orElse(null);
    }

    @Override
    public List<SistemaRiego> findAll() {
        return arduinoRepository.findAll();
    }

    @Override
    public List<SistemaRiego> findByUsuario(Usuario usuario) {
        try {
            return arduinoRepository.findByUsuario(usuario)
                    .orElseThrow(() -> new IllegalArgumentException("Does not have a watering system installed."));
        }catch (Exception e){
             return new ArrayList<>();
        }
    }
    @Override
    public void delete(Integer idSistemaRiego) {
        arduinoRepository.deleteById(idSistemaRiego);
    }
}
