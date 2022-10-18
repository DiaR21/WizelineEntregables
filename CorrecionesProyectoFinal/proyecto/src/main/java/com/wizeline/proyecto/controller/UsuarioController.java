package com.wizeline.proyecto.controller;

import com.wizeline.proyecto.model.Usuario;
import com.wizeline.proyecto.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("Proyecto")
@RequiredArgsConstructor
public class UsuarioController {
    @Autowired
    private final UsuarioService usuarioService;

    @PostMapping("/Usuarios/Guardar")
    public void save(@RequestBody Usuario usuario) {
        usuarioService.save(usuario);
    }
    @GetMapping("/Usuarios")
    @ResponseStatus(HttpStatus.OK)
    public List<Usuario> findAll(){
        return usuarioService.findAll();
    }
    @GetMapping("/Usurio/{id}")
    public Usuario findById(@PathVariable String id){
        return usuarioService.findById(id).get(); }
    @DeleteMapping("/Usurio/Elimina/{id}")
    public String deleteById(@PathVariable String id){
       usuarioService.deleteById(id);
        return "Deleted";
    }
    @PutMapping("/Usurio/Actualizar")
    public void update(@RequestBody Usuario usuario){
        usuarioService.save(usuario);
    }

}
