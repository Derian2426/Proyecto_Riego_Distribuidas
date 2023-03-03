package com.example.practica_seguridad.service;

import com.example.practica_seguridad.model.InformeConsumo;
import com.example.practica_seguridad.repository.IInformeConsumo;
import com.example.practica_seguridad.repository.InformeConsumoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InformeConsumoService implements IInformeConsumo {
    @Autowired
    private InformeConsumoRepository informeConsumoRepository;
    @Override
    public InformeConsumo create(InformeConsumo informeConsumo) {
        return informeConsumoRepository.save(informeConsumo);
    }

    @Override
    public InformeConsumo update(InformeConsumo informeConsumo) {
        return informeConsumoRepository.save(informeConsumo);
    }

    @Override
    public InformeConsumo findById(Integer idInformeConsumo) {
        Optional<InformeConsumo> usuario=informeConsumoRepository.findById(idInformeConsumo);
        return usuario.orElse(null);
    }

    @Override
    public List<InformeConsumo> findAll() {
        return informeConsumoRepository.findAll();
    }

    @Override
    public void delete(Integer idInformeConsumo) {
        informeConsumoRepository.deleteById(idInformeConsumo);
    }
}
