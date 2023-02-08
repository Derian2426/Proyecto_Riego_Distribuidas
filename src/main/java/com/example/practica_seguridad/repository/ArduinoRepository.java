package com.example.practica_seguridad.repository;

import com.example.practica_seguridad.model.DatosSensor;
import com.example.practica_seguridad.model.DatosSensores;
import com.example.practica_seguridad.model.SensorHumedad;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ArduinoRepository extends JpaRepository<DatosSensor,Integer> {
    Optional<DatosSensor> findOneByTipoSensor(String tipoSensor);
}
