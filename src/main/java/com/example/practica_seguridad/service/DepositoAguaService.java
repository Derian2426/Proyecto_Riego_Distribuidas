package com.example.practica_seguridad.service;

import com.example.practica_seguridad.model.DepositoAgua;
import com.example.practica_seguridad.model.ZonaRiego;
import com.example.practica_seguridad.repository.DepositoAguaRepository;
import com.example.practica_seguridad.interfaces.IDepositoAgua;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DepositoAguaService implements IDepositoAgua {
    @Autowired
    private DepositoAguaRepository depositoAguaService;

    @Override
    @Transactional
    public DepositoAgua create(DepositoAgua depositoAgua) {
        try {
            if (depositoAguaService.findByDireccionMAC(depositoAgua.getDireccionMAC()) != null) {
                return new DepositoAgua(-1L, "");
            }
            return depositoAguaService.save(depositoAgua);
        } catch (Exception e) {
            return new DepositoAgua(-1L, e.getMessage());
        }
    }

    @Override
    @Transactional
    public DepositoAgua update(DepositoAgua depositoAgua) {
        try {
            DepositoAgua existingDeposito = depositoAguaService.findByDireccionMAC(depositoAgua.getDireccionMAC());
            if (existingDeposito != null && !existingDeposito.getIdDeposito().equals(depositoAgua.getIdDeposito())) {
                return new DepositoAgua(-1L, "");
            }
            return depositoAguaService.save(depositoAgua);
        } catch (Exception e) {
            return new DepositoAgua(-1L, e.getMessage());
        }
    }

    @Override
    @Transactional
    public DepositoAgua findById(Integer idDepositoAgua) {
        try {
            Optional<DepositoAgua> depositoAgua = depositoAguaService.findById(idDepositoAgua);
            return depositoAgua.orElse(null);
        } catch (Exception e) {
            return new DepositoAgua(-1L, e.getMessage());
        }
    }

    @Override
    @Transactional
    public List<DepositoAgua> findAll() {
        try {
            return depositoAguaService.findAll();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    @Override
    @Transactional
    public void delete(Integer idSensor) {
        try {
            depositoAguaService.deleteById(idSensor);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    @Transactional
    public List<DepositoAgua> findByDescripcion(String descripcion) {
        try {
            return depositoAguaService.findByDescripcionContainingIgnoreCase(descripcion);
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    @Transactional
    public List<DepositoAgua> findByZonaRiego(ZonaRiego zonaRiego) {
        try {
            return depositoAguaService.findByZonaRiego(zonaRiego);
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }
    @Transactional
    public DepositoAgua findByDireccionMAC(String direccionMac) {
        try {
            return depositoAguaService.findByDireccionMAC(direccionMac);
        } catch (Exception e) {
            return new DepositoAgua(-1L, e.getMessage());
        }
    }
}
