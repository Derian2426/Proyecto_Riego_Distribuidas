package com.example.practica_seguridad.service;

import com.example.practica_seguridad.model.Cosecha;
import com.example.practica_seguridad.repository.CosechaRepository;
import com.example.practica_seguridad.repository.ICosechaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CosechaService implements ICosechaService {
    @Autowired
    private CosechaRepository cosechaRepository;
    @Override
    public Cosecha findByName(String name) {
        return cosechaRepository.findOneByNombre(name)
                .orElseThrow(()-> new UsernameNotFoundException("La cosecha: "+ name+" no existe."));
    }

    @Override
    public Cosecha create(Cosecha datosCosecha) {
        return cosechaRepository.save(datosCosecha);
    }

    @Override
    public Cosecha update(Cosecha datosCosecha) {
        return cosechaRepository.save(datosCosecha);
    }

    @Override
    public Cosecha findById(Integer idCosecha) {
        Optional<Cosecha> cosecha=cosechaRepository.findById(idCosecha);
        return cosecha.orElse(null);
    }

    @Override
    public List<Cosecha> findAll() {
        return cosechaRepository.findAll();
    }

    @Override
    public void delete(Integer idCosecha) {
        cosechaRepository.deleteById(idCosecha);
    }
}
