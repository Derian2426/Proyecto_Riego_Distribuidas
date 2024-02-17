package com.example.practica_seguridad.repository;

import com.example.practica_seguridad.model.MonitoreoSuelo;
import com.example.practica_seguridad.model.ZonaRiego;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface MonitoreoSueloRepository extends JpaRepository<MonitoreoSuelo, Integer> {
    @Query(value = "SELECT CURRENT_TIMESTAMP - INTERVAL '5 hours'", nativeQuery = true)
    Date getCurrentDatabaseDateTimeMinusFiveHours();
    Optional<List<MonitoreoSuelo>> findByZonaRiego(ZonaRiego zonaRiego);

    List<MonitoreoSuelo> findByZonaRiegoAndFechaMedicion(ZonaRiego zonaRiego, Date fechaMedicion);
    MonitoreoSuelo findTopByZonaRiegoOrderByIdSueloDesc(ZonaRiego zonaRiego);
}
