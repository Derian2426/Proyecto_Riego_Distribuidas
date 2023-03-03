package com.example.practica_seguridad.controller;

import com.example.practica_seguridad.exeptions.ModelNotFoundException;
import com.example.practica_seguridad.model.DepositoAgua;
import com.example.practica_seguridad.service.DepositoAguaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Deposito")
public class DepositoAguaController {
    @Autowired
    private DepositoAguaService depositoAguaService;
    @GetMapping
    public ResponseEntity<List<DepositoAgua>> listaDeposito(){
        return new ResponseEntity<>(depositoAguaService.findAll(), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<DepositoAgua> createrDeposito(@RequestBody DepositoAgua depositoAgua){
        if (depositoAgua!=null)
            return new ResponseEntity<>(depositoAguaService.create(depositoAgua), HttpStatus.OK);
        else
            return new ResponseEntity<>(new DepositoAgua(), HttpStatus.CONFLICT);
    }
    @PutMapping
    public  ResponseEntity<DepositoAgua> updateUser(@RequestBody DepositoAgua depositoAgua){
        return new ResponseEntity<>(depositoAguaService.update(depositoAgua), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<DepositoAgua> finById(@PathVariable("id") Integer idDepositoAgua){
        DepositoAgua depositoAgua= depositoAguaService.findById(idDepositoAgua);
        if (depositoAgua==null)
            throw new ModelNotFoundException("No se encontro una cosecha");
        return new ResponseEntity<>(depositoAgua,HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") Integer idDepositoAgua) throws Exception {
        DepositoAgua cosecha= depositoAguaService.findById(idDepositoAgua);
        if (cosecha==null)
            throw new ModelNotFoundException("El deposito de agua no se puede eliminar");
        depositoAguaService.delete(idDepositoAgua);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
