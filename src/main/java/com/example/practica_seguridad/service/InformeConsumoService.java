package com.example.practica_seguridad.service;

import com.example.practica_seguridad.model.DepositoAgua;
import com.example.practica_seguridad.model.InformeConsumo;
import com.example.practica_seguridad.interfaces.IInformeConsumo;
import com.example.practica_seguridad.repository.InformeConsumoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class InformeConsumoService implements IInformeConsumo {
    @Autowired
    private InformeConsumoRepository informeConsumoRepository;

    @Override
    @Transactional
    public InformeConsumo create(InformeConsumo informeConsumo) {
        try {
            informeConsumo.setFechaCosumo(new Date());
            return informeConsumoRepository.save(informeConsumo);
        } catch (Exception e) {
            return new InformeConsumo(-1L, 0.0);
        }
    }

    @Override
    @Transactional
    public InformeConsumo update(InformeConsumo informeConsumo) {
        try {
            return informeConsumoRepository.save(informeConsumo);
        } catch (Exception e) {
            return new InformeConsumo(-1L, 0.0);
        }
    }

    @Override
    @Transactional
    public InformeConsumo findById(Integer idInformeConsumo) {
        try {
            Optional<InformeConsumo> usuario = informeConsumoRepository.findById(idInformeConsumo);
            return usuario.orElse(null);
        } catch (Exception e) {
            return new InformeConsumo(-1L, 0.0);
        }
    }

    @Override
    @Transactional
    public List<InformeConsumo> findAll() {
        try {
            return informeConsumoRepository.findAll();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    @Override
    @Transactional
    public void delete(Integer idInformeConsumo) {
        try {
            informeConsumoRepository.deleteById(idInformeConsumo);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    @Transactional
    public List<InformeConsumo> findByFecha(DepositoAgua depositoAgua, Date fecha) {
        try {
            return informeConsumoRepository.findByDepositoAguaAndFechaCosumo(depositoAgua, fecha);
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }
}
