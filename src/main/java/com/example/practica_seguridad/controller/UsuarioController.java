package com.example.practica_seguridad.controller;


import com.example.practica_seguridad.exeptions.ModelNotFoundException;
import com.example.practica_seguridad.model.Usuario;
import com.example.practica_seguridad.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    //shnitzel
    @Autowired
    private UsuarioService usuarioService;
    @GetMapping
    public ResponseEntity<List<Usuario>> listaUsuario(){
        return new ResponseEntity<>(usuarioService.findAll(), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<Usuario> createUser(@RequestBody Usuario usuario){
        usuario.setPassword(new BCryptPasswordEncoder().encode(usuario.getPassword()));
        Usuario user =usuarioService.findByEmail(usuario.getEmail());
        if (user.getEmail()==null)
            return new ResponseEntity<>(usuarioService.create(usuario), HttpStatus.CREATED);
        else
            return new ResponseEntity<>(new Usuario(), HttpStatus.CONFLICT);
    }
    @PutMapping
    public  ResponseEntity<Usuario> updateUser(@RequestBody Usuario usuario){
        return new ResponseEntity<>(usuarioService.update(usuario), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> finById(@PathVariable("id") Integer idUsuario){
        Usuario usuario= usuarioService.findById(idUsuario);
        if (usuario==null)
            throw new ModelNotFoundException("El usuario no fue encontrado!!!");
        return new ResponseEntity<>(usuario,HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") Integer idUsuario) throws Exception {
        Usuario usuario= usuarioService.findById(idUsuario);
        if (usuario==null)
            throw new ModelNotFoundException("El usuario que pretende eliminar no existe!!!");
        usuarioService.delete(idUsuario);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PostMapping("/busquedaUsuario")
    public  ResponseEntity<Usuario> busquedaUser(@RequestBody Usuario usuario){
        return new ResponseEntity<>(usuarioService.findByEmail(usuario.getEmail()), HttpStatus.OK);
    }
}
