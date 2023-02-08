package com.example.practica_seguridad.repository;

import com.example.practica_seguridad.model.Usuario;

import java.util.List;

public interface IUsuarioService {
    Usuario create(Usuario usuario);
    Usuario update(Usuario usuario);
    Usuario findById(Integer idUsuario);
    List<Usuario> findAll();
    void delete(Integer idUsuario);
}
