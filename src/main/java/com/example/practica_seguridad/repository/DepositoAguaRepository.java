package com.example.practica_seguridad.repository;

import com.example.practica_seguridad.model.DepositoAgua;
import com.example.practica_seguridad.model.ZonaRiego;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DepositoAguaRepository extends JpaRepository<DepositoAgua, Integer> {
    DepositoAgua findByDescripcion(String depositoAgua);

    List<DepositoAgua> findByDescripcionContainingIgnoreCase(String depositoAgua);
    List<DepositoAgua> findByZonaRiego(ZonaRiego zonaRiego);
    DepositoAgua findByDireccionMAC(String direccionMac);
}
