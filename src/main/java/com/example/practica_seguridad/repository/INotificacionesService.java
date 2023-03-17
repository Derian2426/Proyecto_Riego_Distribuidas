package com.example.practica_seguridad.repository;

import com.example.practica_seguridad.model.Notificacion;
import com.example.practica_seguridad.model.Usuario;

import java.util.List;

public interface INotificacionesService {
    Notificacion create(Notificacion notificacionsensor);
    Notificacion update(Notificacion notificacion);
    Notificacion findById(Integer idNotificacion);
    List<Notificacion> findAll();
    void delete(Integer idNotificacion);
    List<Notificacion> findByUsuario(Usuario usuario);
}
