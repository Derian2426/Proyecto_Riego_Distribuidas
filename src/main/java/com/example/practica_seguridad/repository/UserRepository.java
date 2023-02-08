package com.example.practica_seguridad.repository;
import com.example.practica_seguridad.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Usuario,Integer> {

}
