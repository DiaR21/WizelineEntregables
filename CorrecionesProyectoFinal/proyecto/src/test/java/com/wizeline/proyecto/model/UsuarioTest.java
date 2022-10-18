package com.wizeline.proyecto.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UsuarioTest {

    @Test
    public void nuevoUsuarioAgregaNombre() {
        Usuario nuevoUsuario = new Usuario("Diana","d@gmail.com");
        nuevoUsuario.setNombre("Lizbeth");

        String nombre = nuevoUsuario.getNombre();

        assertEquals ("Lizbeth", nombre);
    }


    @Test
    public void nuevoUsuarioRegresaEmail() {
        Usuario nuevoUsuario = new Usuario("Diana","d@gmail.com");
        nuevoUsuario.setEmail("d2@gmail.com");
        String email = nuevoUsuario.getEmail();
        assertEquals ("d2@gmail.com",email);
    }
}