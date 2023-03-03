package com.example.practica_seguridad.repository;

import com.example.practica_seguridad.model.Cosecha;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CosechaRepository extends JpaRepository<Cosecha,Integer> {
    Optional<Cosecha> findOneByNombre(String nombre);
}
