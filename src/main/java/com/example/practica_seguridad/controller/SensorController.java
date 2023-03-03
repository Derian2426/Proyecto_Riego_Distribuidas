package com.example.practica_seguridad.controller;

import com.example.practica_seguridad.exeptions.ModelNotFoundException;
import com.example.practica_seguridad.model.Sensor;
import com.example.practica_seguridad.model.ZonaRiego;
import com.example.practica_seguridad.service.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Sensor")
public class SensorController {
    @Autowired
    private SensorService sensorService;
    @GetMapping
    public ResponseEntity<List<Sensor>> listaSensor(){
        return new ResponseEntity<>(sensorService.findAll(), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<Sensor> createSensor(@RequestBody Sensor sensor){
        if (sensor!=null)
            return new ResponseEntity<>(sensorService.create(sensor), HttpStatus.CREATED);
        else
            return new ResponseEntity<>(new Sensor(), HttpStatus.CONFLICT);
    }
    @PutMapping
    public  ResponseEntity<Sensor> updateSensor(@RequestBody Sensor sensor){
        return new ResponseEntity<>(sensorService.update(sensor), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Sensor> finById(@PathVariable("id") Integer idSensor){
        Sensor zonaRiego= sensorService.findById(idSensor);
        if (zonaRiego==null)
            throw new ModelNotFoundException("El sensor no fue encontrado!!!");
        return new ResponseEntity<>(zonaRiego,HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") Integer idSensor) throws Exception {
        Sensor zonaRiego= sensorService.findById(idSensor);
        if (zonaRiego==null)
            throw new ModelNotFoundException("La zona de riego que pretende eliminar no existe!!!");
        sensorService.delete(idSensor);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
