package com.example.practica_seguridad.controller;

import com.example.practica_seguridad.model.SistemaRiego;
import com.example.practica_seguridad.model.Usuario;
import com.example.practica_seguridad.security.TokenUtils;
import com.example.practica_seguridad.service.ArduinoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/autorizacion")
public class ArduinoController {

    @Autowired
    private ArduinoService arduinoService;
    @PostMapping
    public String verificarSistema(@RequestBody SistemaRiego sistemaRiego){
        if(arduinoService.findByName(sistemaRiego.getNombre())!=null){
            return TokenUtils.createToken(sistemaRiego.getNombre(),String.valueOf(sistemaRiego.getTipo()));
        }else{
            return "No se logro verificar el sensor";
        }
    }
    @PostMapping("/registroSistema")
    public void registrarSistema(@RequestBody SistemaRiego sistemaRiego){
        if (sistemaRiego!=null)
            arduinoService.create(sistemaRiego);
        else
            System.out.println("Ocurrio un error");
    }
    @PostMapping("/listaSistema")
    public ResponseEntity<List<SistemaRiego>> listaSistema(@RequestBody Usuario usuario){
        return new ResponseEntity<>(arduinoService.findByUsuario(usuario), HttpStatus.OK);
    }

}
