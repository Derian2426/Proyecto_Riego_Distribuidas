package com.example.practica_seguridad.controller;

import com.example.practica_seguridad.exeptions.ModelNotFoundException;
import com.example.practica_seguridad.model.Cosecha;
import com.example.practica_seguridad.model.Notificacion;
import com.example.practica_seguridad.model.SistemaRiego;
import com.example.practica_seguridad.model.Usuario;
import com.example.practica_seguridad.service.NotificacionesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notificaciones")
public class NotificacionesController {
    @Autowired
    private NotificacionesService notificacionesService;
    @PostMapping("/listaNotificaciones")
    public ResponseEntity<List<Notificacion>> listaNotificaciones(@RequestBody Usuario usuario){
        return new ResponseEntity<>(notificacionesService.findByUsuario(usuario), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<Notificacion> createrNotificacion(@RequestBody Notificacion notificacion){
        if (notificacion!=null)
            return new ResponseEntity<>(notificacionesService.create(notificacion), HttpStatus.OK);
        else
            return new ResponseEntity<>(new Notificacion(), HttpStatus.CONFLICT);
    }
    @PutMapping
    public  ResponseEntity<Notificacion> updateNotificacion(@RequestBody Notificacion notificacion){
        return new ResponseEntity<>(notificacionesService.update(notificacion), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") Integer idNotificacion) throws Exception {
        Notificacion notificacion= notificacionesService.findById(idNotificacion);
        if (notificacion==null)
            throw new ModelNotFoundException("La notificación no se puede eliminar");
        notificacionesService.delete(idNotificacion);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
