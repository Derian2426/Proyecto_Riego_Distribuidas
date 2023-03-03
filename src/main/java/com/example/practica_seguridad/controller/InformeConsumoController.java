package com.example.practica_seguridad.controller;

import com.example.practica_seguridad.exeptions.ModelNotFoundException;
import com.example.practica_seguridad.model.Cosecha;
import com.example.practica_seguridad.model.InformeConsumo;
import com.example.practica_seguridad.service.InformeConsumoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Informe")
public class InformeConsumoController {
    @Autowired
    private InformeConsumoService informeConsumoService;
    @GetMapping
    public ResponseEntity<List<InformeConsumo>> listaInforme(){
        return new ResponseEntity<>(informeConsumoService.findAll(), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<InformeConsumo> createrInforme(@RequestBody InformeConsumo informeConsumo){
        if (informeConsumo!=null)
            return new ResponseEntity<>(informeConsumoService.create(informeConsumo), HttpStatus.OK);
        else
            return new ResponseEntity<>(new InformeConsumo(), HttpStatus.CONFLICT);
    }
    @PutMapping
    public  ResponseEntity<InformeConsumo> updateUser(@RequestBody InformeConsumo informeConsumo){
        return new ResponseEntity<>(informeConsumoService.update(informeConsumo), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<InformeConsumo> finById(@PathVariable("id") Integer idInformeConsumo){
        InformeConsumo informeConsumo= informeConsumoService.findById(idInformeConsumo);
        if (informeConsumo==null)
            throw new ModelNotFoundException("No se encontro una cosecha");
        return new ResponseEntity<>(informeConsumo,HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") Integer idInformeConsumo) throws Exception {
        InformeConsumo informeConsumo= informeConsumoService.findById(idInformeConsumo);
        if (informeConsumo==null)
            throw new ModelNotFoundException("La cosecha no se puede eliminar");
        informeConsumoService.delete(idInformeConsumo);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
