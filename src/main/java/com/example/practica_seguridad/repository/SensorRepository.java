package com.example.practica_seguridad.repository;

import com.example.practica_seguridad.model.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SensorRepository extends JpaRepository<Sensor,Integer> {
}
