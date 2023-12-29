package com.example.practica_seguridad.controller;

import com.example.practica_seguridad.model.*;
import com.example.practica_seguridad.security.TokenUtils;
import com.example.practica_seguridad.service.ZonaRiegoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/zonaRiego")
public class ZonaRiegoController {
    @Autowired
    private ZonaRiegoService zonaRiegoService;
    private ZonaRiego zonaRiego;

    @GetMapping("/zonas")
    public ResponseEntity<List<ZonaRiego>> listaZonaRiego() {
        try {
            return new ResponseEntity<>(zonaRiegoService.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
        }
    }

    @PostMapping("/autorizacion")
    public String verificarZona(@RequestBody ZonaRiego zonaRiego) {
        if (zonaRiegoService.findByDireccionMac(zonaRiego.getDireccionMAC()) != null) {
            return TokenUtils.createToken(zonaRiego.getNombreZona(), String.valueOf(zonaRiego.getDireccionMAC()));
        } else {
            return "No se logro verificar el sensor";
        }
    }

    @PostMapping("/registro")
    public ResponseEntity<ZonaRiego> createZonaRiego(@RequestBody DatosSensoresZonas zonaRiegoRequest) {
        try {
            if (zonaRiegoRequest == null) {
                return new ResponseEntity<>(new ZonaRiego(-1L, "Ocurrió un error. Vuelva a intentarlo luego."), HttpStatus.BAD_REQUEST);
            }
            zonaRiego = zonaRiegoService.create(zonaRiegoRequest);
            if (zonaRiego != null && zonaRiego.getIdZona() > 0) {
                return new ResponseEntity<>(zonaRiego, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new ZonaRiego(-1L, zonaRiegoRequest.getZonas().getNombreZona()), HttpStatus.OK);
            }
        } catch (Exception ex) {
            return new ResponseEntity<>(new ZonaRiego(-1L, "Ocurrió un error inesperado."), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/actualizar")
    public ResponseEntity<ZonaRiego> updateZonaRiego(@RequestBody DatosSensoresZonas zonaRiegoRequest) {
        try {
            if (zonaRiegoRequest == null) {
                return new ResponseEntity<>(new ZonaRiego(-1L, "Ocurrió un error. Vuelva a intentarlo luego."), HttpStatus.BAD_REQUEST);
            }
            zonaRiego = zonaRiegoService.update(zonaRiegoRequest);
            if (zonaRiego != null && zonaRiego.getIdZona() > 0) {
                return new ResponseEntity<>(zonaRiego, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new ZonaRiego(-1L, zonaRiegoRequest.getZonas().getNombreZona()), HttpStatus.OK);
            }
        } catch (Exception ex) {
            return new ResponseEntity<>(new ZonaRiego(-1L, "Ocurrió un error inesperado."), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/actualizarmonitoreo")
    public ResponseEntity<ZonaRiego> updateZonaRiego(@RequestBody ZonaRiego zonaRiegoRequest) {
        try {
            if (zonaRiegoRequest == null) {
                return new ResponseEntity<>(new ZonaRiego(-1L, "Ocurrió un error. Vuelva a intentarlo luego."), HttpStatus.BAD_REQUEST);
            }
            zonaRiego = zonaRiegoService.updateZona(zonaRiegoRequest);
            if (zonaRiego != null && zonaRiego.getIdZona() > 0) {
                return new ResponseEntity<>(zonaRiego, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new ZonaRiego(-1L, zonaRiegoRequest.getNombreZona()), HttpStatus.OK);
            }
        } catch (Exception ex) {
            return new ResponseEntity<>(new ZonaRiego(-1L, "Ocurrió un error inesperado."), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ZonaRiego> delete(@PathVariable("id") Integer idzonaRiego) throws Exception {
        try {
            zonaRiego = zonaRiegoService.findById(idzonaRiego);
            if (zonaRiego == null)
                return new ResponseEntity<>(new ZonaRiego(-1L, "Ocurrió un error inesperado."), HttpStatus.CONFLICT);
            zonaRiegoService.delete(idzonaRiego);
            return new ResponseEntity<>(zonaRiego, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ZonaRiego(-1L, "Ocurrió un error inesperado."), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/listaZonaRiego")
    public ResponseEntity<List<ZonaRiego>> listaSistema(@RequestBody SistemaRiego sistemaRiego) {
        try {
            List<ZonaRiego> fila = zonaRiegoService.findBySistemaRiego(sistemaRiego);
            return new ResponseEntity<>(fila, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
        }
    }

    @PostMapping("/busqueda")
    public ResponseEntity<List<ZonaRiego>> busquedaZona(@RequestBody ZonaRiego zonaRequest) {
        try {
            if (zonaRequest == null)
                return new ResponseEntity<>(zonaRiegoService.findAll(), HttpStatus.OK);
            else
                return new ResponseEntity<>(zonaRiegoService.findByNombre(zonaRequest.getNombreZona()), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(zonaRiegoService.findAll(), HttpStatus.OK);
        }
    }

    @PostMapping("/listasensores")
    public ResponseEntity<List<Sensor>> listaEnfermedad(@RequestBody ZonaRiego zonaRequest) {
        try {
            return new ResponseEntity<>(zonaRiegoService.findListaSensores(zonaRequest), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
        }
    }

    @GetMapping("/{direccionMAC}")
    public ResponseEntity<ZonaRiego> buscarDireccionMac(@PathVariable("direccionMAC") String direccionMAC) {
        try {
            zonaRiego = zonaRiegoService.findByDireccionMac(direccionMAC);
            if (zonaRiego == null)
                return new ResponseEntity<>(new ZonaRiego(-1L, "Ocurrió un error inesperado."), HttpStatus.CONFLICT);
            return new ResponseEntity<>(zonaRiego, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ZonaRiego(-1L, "Ocurrió un error inesperado."), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
