package com.example.practica_seguridad.controller;

import com.example.practica_seguridad.model.DatosSensor;
import com.example.practica_seguridad.security.TokenUtils;
import com.example.practica_seguridad.service.ArduinoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/autorizacion")
public class ArduinoController {

    @Autowired
    private ArduinoService arduinoService;
    @PostMapping
    public String verificarSensor(@RequestBody DatosSensor sensor){
        if(arduinoService.findByName(sensor.getTipoSensor())!=null){
            return TokenUtils.createToken(sensor.getTipoSensor(),String.valueOf(sensor.getLectura()));
        }else{
            return "No se logro verificar el sensor";
        }
    }
    @PostMapping("/datoSensor")
    public void recibeDatoSensor(@RequestBody DatosSensor sensor){
        if (sensor!=null)
            arduinoService.create(sensor);
        else
            System.out.println("Ocurrio un error");
    }

}
