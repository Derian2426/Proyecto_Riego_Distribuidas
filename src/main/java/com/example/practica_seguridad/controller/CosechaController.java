package com.example.practica_seguridad.controller;

import com.example.practica_seguridad.exeptions.ModelNotFoundException;
import com.example.practica_seguridad.model.Cosecha;
import com.example.practica_seguridad.service.CosechaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cosecha")
public class CosechaController {
    @Autowired
    private CosechaService cosechaService;
    @GetMapping
    public ResponseEntity<List<Cosecha>> listaCosecha(){
        return new ResponseEntity<>(cosechaService.findAll(), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<Cosecha> createrCosecha(@RequestBody Cosecha cosecha){
        if (cosecha!=null)
            return new ResponseEntity<>(cosechaService.create(cosecha), HttpStatus.OK);
        else
            return new ResponseEntity<>(new Cosecha(), HttpStatus.CONFLICT);
    }
    @PutMapping
    public  ResponseEntity<Cosecha> updateUser(@RequestBody Cosecha cosecha){
        return new ResponseEntity<>(cosechaService.update(cosecha), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Cosecha> finById(@PathVariable("id") Integer idCosecha){
        Cosecha cosecha= cosechaService.findById(idCosecha);
        if (cosecha==null)
            throw new ModelNotFoundException("No se encontro una cosecha");
        return new ResponseEntity<>(cosecha,HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") Integer idCosecha) throws Exception {
        Cosecha cosecha= cosechaService.findById(idCosecha);
        if (cosecha==null)
            throw new ModelNotFoundException("La cosecha no se puede eliminar");
        cosechaService.delete(idCosecha);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
