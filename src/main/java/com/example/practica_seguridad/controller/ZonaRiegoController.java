package com.example.practica_seguridad.controller;

import com.example.practica_seguridad.exeptions.ModelNotFoundException;
import com.example.practica_seguridad.model.SistemaRiego;
import com.example.practica_seguridad.model.Usuario;
import com.example.practica_seguridad.model.ZonaRiego;
import com.example.practica_seguridad.service.ZonaRiegoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/zonaRiego")
public class ZonaRiegoController {
    @Autowired
    private ZonaRiegoService zonaRiegoService;
    @GetMapping
    public ResponseEntity<List<ZonaRiego>> listaZonaRiego(){
        return new ResponseEntity<>(zonaRiegoService.findAll(), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<ZonaRiego> createZonaRiego(@RequestBody ZonaRiego zonaRiego){
        if (zonaRiego!=null)
            return new ResponseEntity<>(zonaRiegoService.create(zonaRiego), HttpStatus.CREATED);
        else
            return new ResponseEntity<>(new ZonaRiego(), HttpStatus.CONFLICT);
    }
    @PutMapping
    public  ResponseEntity<ZonaRiego> updateZonaRiego(@RequestBody ZonaRiego zonaRiego){
        return new ResponseEntity<>(zonaRiegoService.update(zonaRiego), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ZonaRiego> finById(@PathVariable("id") Integer idzonaRiego){
        ZonaRiego zonaRiego= zonaRiegoService.findById(idzonaRiego);
        if (zonaRiego==null)
            throw new ModelNotFoundException("La zona de riego no fue encontrado!!!");
        return new ResponseEntity<>(zonaRiego,HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") Integer idzonaRiego) throws Exception {
        ZonaRiego zonaRiego= zonaRiegoService.findById(idzonaRiego);
        if (zonaRiego==null)
            throw new ModelNotFoundException("La zona de riego que pretende eliminar no existe!!!");
        zonaRiegoService.delete(idzonaRiego);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PostMapping("/listaZonaRiego")
    public ResponseEntity<List<ZonaRiego>> listaSistema(@RequestBody SistemaRiego sistemaRiego){
        return new ResponseEntity<>(zonaRiegoService.findBySistemaRiego(sistemaRiego), HttpStatus.OK);
    }
}
