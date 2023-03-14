package com.example.practica_seguridad.controller;

import com.example.practica_seguridad.model.DatosSensoresZonas;
import com.example.practica_seguridad.model.DetalleSensor;
import com.example.practica_seguridad.model.Sensor;
import com.example.practica_seguridad.model.ZonaRiego;
import com.example.practica_seguridad.service.DetalleSensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/Sensor")
public class DetalleSensorController {
    @Autowired
    private DetalleSensorService detalleSensorService;
    @PostMapping("/datosSensoresZonas")
    public void recibirDatosSensoresZonas(@RequestBody DatosSensoresZonas datosSensoresZonas) {
        List<Sensor> sensores = datosSensoresZonas.getSensores();
        List<ZonaRiego> zonas = datosSensoresZonas.getZonas();
        List<DetalleSensor> detalles = new ArrayList<>();
        for (int i = 0; i < sensores.size(); i++) {
            Sensor sensor = sensores.get(i);
            ZonaRiego zona = zonas.get(i);
            DetalleSensor detalleSensor = new DetalleSensor();
            detalleSensor.setSensor(sensor);
            detalleSensor.setZona(zona);
            detalles.add(detalleSensor);
        }
        detalleSensorService.saveAll(detalles);
    }
}
