package com.example.practica_seguridad.repository;

import com.example.practica_seguridad.model.DepositoAgua;
import com.example.practica_seguridad.model.InformeConsumo;

import java.util.List;

public interface IInformeConsumo {
    InformeConsumo create(InformeConsumo informeConsumo);
    InformeConsumo update(InformeConsumo informeConsumo);
    InformeConsumo findById(Integer idInformeConsumo);
    List<InformeConsumo> findAll();
    void delete(Integer idInformeConsumo);
}
