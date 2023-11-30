package com.example.practica_seguridad.controller;

import com.example.practica_seguridad.model.FiltroMonitoreoSuelo;
import com.example.practica_seguridad.model.MonitoreoSuelo;
import com.example.practica_seguridad.model.ZonaRiego;
import com.example.practica_seguridad.service.MonitoreoSueloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/MonitoreoSuelo")
public class MonitoreoSueloController {
    @Autowired
    private MonitoreoSueloService monitoreoSueloService;

    @GetMapping("/monitoreosuelos")
    public ResponseEntity<List<MonitoreoSuelo>> listaMonitoreo() {
        try {
            return new ResponseEntity<>(monitoreoSueloService.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
        }
    }

    @PostMapping("/registro")
    public ResponseEntity<MonitoreoSuelo> createrMonitoreoSuelo(@RequestBody MonitoreoSuelo monitoreoSuelo) {
        try {
            if (monitoreoSuelo != null)
                return new ResponseEntity<>(monitoreoSueloService.create(monitoreoSuelo), HttpStatus.OK);
            else
                return new ResponseEntity<>(new MonitoreoSuelo(-1L, "Ocurrió un error. Vuelva a intentarlo luego."), HttpStatus.CONFLICT);
        } catch (Exception ex) {
            return new ResponseEntity<>(new MonitoreoSuelo(-1L, "Ocurrió un error inesperado."), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/actualizar")
    public ResponseEntity<MonitoreoSuelo> updateMonitoreoSuelo(@RequestBody MonitoreoSuelo monitoreoSuelo) {
        try {
            if (monitoreoSuelo != null)
                return new ResponseEntity<>(monitoreoSueloService.update(monitoreoSuelo), HttpStatus.OK);
            else
                return new ResponseEntity<>(new MonitoreoSuelo(-1L, "Ocurrió un error. Vuelva a intentarlo luego."), HttpStatus.CONFLICT);
        } catch (Exception ex) {
            return new ResponseEntity<>(new MonitoreoSuelo(-1L, "Ocurrió un error inesperado."), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MonitoreoSuelo> delete(@PathVariable("id") Integer idMonitoreoSuelo) throws Exception {
        try {
            MonitoreoSuelo monitoreoSuelo = monitoreoSueloService.findById(idMonitoreoSuelo);
            if (monitoreoSuelo == null)
                return new ResponseEntity<>(new MonitoreoSuelo(-1L, "Ocurrió un error inesperado."), HttpStatus.CONFLICT);
            monitoreoSueloService.delete(idMonitoreoSuelo);
            return new ResponseEntity<>(monitoreoSuelo, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new MonitoreoSuelo(-1L, "Ocurrió un error inesperado."), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/listamonitoreosuelo")
    public ResponseEntity<List<MonitoreoSuelo>> listaMonitoreoSuelo(@RequestBody ZonaRiego zonaRiego) {
        try {
            return new ResponseEntity<>(monitoreoSueloService.findAllSuelo(zonaRiego), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
        }
    }

    @PostMapping("/busqueda")
    public ResponseEntity<List<MonitoreoSuelo>> busquedaMonitoreoSuelo(@RequestBody FiltroMonitoreoSuelo filtroMonitoreoSuelo) {
        try {
            if (filtroMonitoreoSuelo == null)
                return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
            else
                return new ResponseEntity<>(monitoreoSueloService.findByFecha(filtroMonitoreoSuelo.getZonaRiego(), filtroMonitoreoSuelo.getFechaMedicion()), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
        }
    }
}
