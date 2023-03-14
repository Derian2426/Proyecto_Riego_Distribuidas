package com.example.practica_seguridad.controller;

import com.example.practica_seguridad.exeptions.ModelNotFoundException;
import com.example.practica_seguridad.model.MonitoreoTemperatura;
import com.example.practica_seguridad.model.ZonaRiego;
import com.example.practica_seguridad.service.MonitoreoTemperaturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/MonitoreoTemperatura")
public class MonitoreoTemperaturaController {
    @Autowired
    private MonitoreoTemperaturaService monitoreoTemperaturaService;
    @GetMapping
    public ResponseEntity<List<MonitoreoTemperatura>> listaMonitoreo(){
        return new ResponseEntity<>(monitoreoTemperaturaService.findAll(), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<MonitoreoTemperatura> createrMonitoreoTemperatura(@RequestBody MonitoreoTemperatura monitoreoTemperatura){
        if (monitoreoTemperatura!=null)
            return new ResponseEntity<>(monitoreoTemperaturaService.create(monitoreoTemperatura), HttpStatus.OK);
        else
            return new ResponseEntity<>(new MonitoreoTemperatura(), HttpStatus.CONFLICT);
    }
    @PutMapping
    public  ResponseEntity<MonitoreoTemperatura> updateMonitoreoTemperatura(@RequestBody MonitoreoTemperatura monitoreoTemperatura){
        return new ResponseEntity<>(monitoreoTemperaturaService.update(monitoreoTemperatura), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<MonitoreoTemperatura> finById(@PathVariable("id") Integer idMonitoreoTemperatura){
        MonitoreoTemperatura monitoreoSuelo= monitoreoTemperaturaService.findById(idMonitoreoTemperatura);
        if (monitoreoSuelo==null)
            throw new ModelNotFoundException("No se encontro el registro");
        return new ResponseEntity<>(monitoreoSuelo,HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") Integer idMonitoreoTemperatura) throws Exception {
        MonitoreoTemperatura monitoreoSuelo= monitoreoTemperaturaService.findById(idMonitoreoTemperatura);
        if (monitoreoSuelo==null)
            throw new ModelNotFoundException("La cosecha no se puede eliminar");
        monitoreoTemperaturaService.delete(idMonitoreoTemperatura);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PostMapping("/temperatura")
    public ResponseEntity<List<MonitoreoTemperatura>> listaMonitoreoTemperatura(@RequestBody ZonaRiego zonaRiego){
        return new ResponseEntity<>(monitoreoTemperaturaService.findAllTemperatura(zonaRiego), HttpStatus.OK);
    }
}
