package com.example.practica_seguridad.service;
import com.example.practica_seguridad.model.SistemaRiego;
import com.example.practica_seguridad.model.ZonaRiego;
import com.example.practica_seguridad.repository.IZonaRiegoService;
import com.example.practica_seguridad.repository.ZonaRiegoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ZonaRiegoService implements IZonaRiegoService {
    @Autowired
    private ZonaRiegoRepository zonaRiegoRepository;
    @Override
    public ZonaRiego create(ZonaRiego zonaRiego) {
        return zonaRiegoRepository.save(zonaRiego);
    }

    @Override
    public ZonaRiego update(ZonaRiego zonaRiego) {
        return zonaRiegoRepository.save(zonaRiego);
    }

    @Override
    public ZonaRiego findById(Integer idzonaRiego) {
        Optional<ZonaRiego> zonaRiego=zonaRiegoRepository.findById(idzonaRiego);
        return zonaRiego.orElse(null);
    }

    @Override
    public List<ZonaRiego> findAll() {
        return zonaRiegoRepository.findAll();
    }

    @Override
    public void delete(Integer idzonaRiego) {
        zonaRiegoRepository.deleteById(idzonaRiego);
    }

    @Override
    public List<ZonaRiego> findBySistemaRiego(SistemaRiego sistemaRiego) {
        try {
            return zonaRiegoRepository.findBySistemaRiego(sistemaRiego)
                    .orElseThrow(() -> new IllegalArgumentException("Does not have a watering system installed."));
        }catch (Exception e){
            return new ArrayList<>();
        }
    }
}
