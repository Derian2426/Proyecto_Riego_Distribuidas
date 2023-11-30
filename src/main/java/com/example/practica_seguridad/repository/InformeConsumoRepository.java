package com.example.practica_seguridad.repository;

import com.example.practica_seguridad.model.DepositoAgua;
import com.example.practica_seguridad.model.InformeConsumo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface InformeConsumoRepository extends JpaRepository<InformeConsumo,Integer> {
    List<InformeConsumo> findByDepositoAguaAndFechaCosumo(DepositoAgua depositoAgua, Date fechaMedicion);
}
