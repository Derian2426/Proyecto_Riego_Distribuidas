package com.example.practica_seguridad.controller;

import com.example.practica_seguridad.exeptions.ModelNotFoundException;
import com.example.practica_seguridad.model.InformeConsumo;
import com.example.practica_seguridad.model.MonitoreoSuelo;
import com.example.practica_seguridad.service.InformeConsumoService;
import com.example.practica_seguridad.service.MonitoreoSueloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/MonitoreoSuelo")
public class MonitoreoSueloController {
    @Autowired
    private MonitoreoSueloService monitoreoSueloService;
    @GetMapping
    public ResponseEntity<List<MonitoreoSuelo>> listaMonitoreo(){
        return new ResponseEntity<>(monitoreoSueloService.findAll(), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<MonitoreoSuelo> createrMonitoreoSuelo(@RequestBody MonitoreoSuelo monitoreoSuelo){
        if (monitoreoSuelo!=null)
            return new ResponseEntity<>(monitoreoSueloService.create(monitoreoSuelo), HttpStatus.OK);
        else
            return new ResponseEntity<>(new MonitoreoSuelo(), HttpStatus.CONFLICT);
    }
    @PutMapping
    public  ResponseEntity<MonitoreoSuelo> updateMonitoreoSuelo(@RequestBody MonitoreoSuelo monitoreoSuelo){
        return new ResponseEntity<>(monitoreoSueloService.update(monitoreoSuelo), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<MonitoreoSuelo> finById(@PathVariable("id") Integer idMonitoreoSuelo){
        MonitoreoSuelo monitoreoSuelo= monitoreoSueloService.findById(idMonitoreoSuelo);
        if (monitoreoSuelo==null)
            throw new ModelNotFoundException("No se encontro el registro");
        return new ResponseEntity<>(monitoreoSuelo,HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") Integer idMonitoreoSuelo) throws Exception {
        MonitoreoSuelo monitoreoSuelo= monitoreoSueloService.findById(idMonitoreoSuelo);
        if (monitoreoSuelo==null)
            throw new ModelNotFoundException("La cosecha no se puede eliminar");
        monitoreoSueloService.delete(idMonitoreoSuelo);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
