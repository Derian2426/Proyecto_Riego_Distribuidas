package com.example.practica_seguridad.service;

import com.example.practica_seguridad.model.Usuario;
import com.example.practica_seguridad.repository.IUsuarioService;
import com.example.practica_seguridad.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService implements IUsuarioService {
    @Autowired
    private UserRepository usuarioRepository;

    @Override
    public Usuario create(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Override
    public Usuario update(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Override
    public Usuario findById(Integer idUsuario) {
        Optional<Usuario> usuario=usuarioRepository.findById(idUsuario);
        return usuario.orElse(null);
    }

    @Override
    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    @Override
    public void delete(Integer idUsuario) {
        usuarioRepository.deleteById(idUsuario);
    }

}
