package com.example.practica_seguridad.repository;

import com.example.practica_seguridad.model.Notificacion;
import com.example.practica_seguridad.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface NotificacionesRepository extends JpaRepository<Notificacion,Integer> {
    Optional<List<Notificacion>> findByUsuario(Usuario usuario);
}
