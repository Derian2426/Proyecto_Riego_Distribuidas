package com.example.practica_seguridad.repository;

import com.example.practica_seguridad.model.MonitoreoSuelo;
import com.example.practica_seguridad.model.ZonaRiego;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MonitoreoSueloRepository extends JpaRepository<MonitoreoSuelo,Integer> {
    Optional<List<MonitoreoSuelo>> findByZonaRiego(ZonaRiego zonaRiego);
}
