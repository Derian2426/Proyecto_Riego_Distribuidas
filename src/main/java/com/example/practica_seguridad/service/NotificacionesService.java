package com.example.practica_seguridad.service;

import com.example.practica_seguridad.model.Notificacion;
import com.example.practica_seguridad.model.Usuario;
import com.example.practica_seguridad.repository.INotificacionesService;
import com.example.practica_seguridad.repository.NotificacionesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class NotificacionesService implements INotificacionesService {
    @Autowired
    private NotificacionesRepository notificacionesRepository;

    @Override
    public Notificacion create(Notificacion notificacionsensor) {
        return notificacionesRepository.save(notificacionsensor);
    }

    @Override
    public Notificacion update(Notificacion notificacion) {
        return notificacionesRepository.save(notificacion);
    }

    @Override
    public Notificacion findById(Integer idNotificacion) {
        Optional<Notificacion> notificacion=notificacionesRepository.findById(idNotificacion);
        return notificacion.orElse(null);
    }

    @Override
    public List<Notificacion> findAll() {
        return notificacionesRepository.findAll();
    }

    @Override
    public void delete(Integer idNotificacion) {
        notificacionesRepository.deleteById(idNotificacion);
    }

    @Override
    public List<Notificacion> findByUsuario(Usuario usuario) {
        try {
            return notificacionesRepository.findByUsuario(usuario)
                    .orElseThrow(() -> new IllegalArgumentException("Does not have a watering system installed."));
        }catch (Exception e){
            return new ArrayList<>();
        }
    }
}
