package com.example.practica_seguridad.service;

import com.example.practica_seguridad.model.DepositoAgua;
import com.example.practica_seguridad.repository.DepositoAguaRepository;
import com.example.practica_seguridad.repository.IDepositoAgua;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepositoAguaService implements IDepositoAgua {
    @Autowired
    private DepositoAguaRepository depositoAguaService;
    @Override
    public DepositoAgua create(DepositoAgua depositoAgua) {
        return depositoAguaService.save(depositoAgua);
    }

    @Override
    public DepositoAgua update(DepositoAgua depositoAgua) {
        return depositoAguaService.save(depositoAgua);
    }

    @Override
    public DepositoAgua findById(Integer idDepositoAgua) {
        Optional<DepositoAgua> depositoAgua=depositoAguaService.findById(idDepositoAgua);
        return depositoAgua.orElse(null);
    }

    @Override
    public List<DepositoAgua> findAll() {
        return depositoAguaService.findAll();
    }

    @Override
    public void delete(Integer idSensor) {
        depositoAguaService.deleteById(idSensor);
    }
}
