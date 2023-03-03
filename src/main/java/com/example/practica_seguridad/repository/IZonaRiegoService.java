package com.example.practica_seguridad.repository;

import com.example.practica_seguridad.model.SistemaRiego;
import com.example.practica_seguridad.model.Usuario;
import com.example.practica_seguridad.model.ZonaRiego;

import java.util.List;

public interface IZonaRiegoService {
    ZonaRiego create(ZonaRiego zonaRiego);
    ZonaRiego update(ZonaRiego zonaRiego);
    ZonaRiego findById(Integer idzonaRiego);
    List<ZonaRiego> findAll();
    void delete(Integer idzonaRiego);
    List<ZonaRiego> findBySistemaRiego(SistemaRiego sistemaRiego);
}
