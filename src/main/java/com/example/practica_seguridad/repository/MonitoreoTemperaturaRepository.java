package com.example.practica_seguridad.repository;

import com.example.practica_seguridad.model.MonitoreoTemperatura;
import com.example.practica_seguridad.model.ZonaRiego;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MonitoreoTemperaturaRepository extends JpaRepository<MonitoreoTemperatura,Integer> {
    Optional<List<MonitoreoTemperatura>> findByZonaRiego(ZonaRiego zonaRiego);
}
