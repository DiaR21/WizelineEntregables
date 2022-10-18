package com.wizeline.proyecto.controller;

import com.wizeline.proyecto.model.Usuario;
import com.wizeline.proyecto.service.UsuarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UsuarioControllerTest {

    /**GET all*/
    @Mock
    private UsuarioService usuarioService;
    @InjectMocks
    private  UsuarioController  usuarioController;
    @Test
    public void traesUsuarios(){
        List<Usuario> usuario0=List.of(new Usuario("1","Diana","dr@gmail.com",26,55), new Usuario("2","Lizbeth","lr@gmail.com",26,55));
        when(usuarioService.findAll()).thenReturn(usuario0);
        List<Usuario> usuario1 = usuarioController.findAll();
        assertEquals(usuario0, usuario1);
    }
    /**GET id */
    @Test
    public void traesUnUsuario() {
        Usuario usuario = new Usuario("3","Diana","dr@gmail.com",26,55);
        when(usuarioService.findById("3")).thenReturn(java.util.Optional.of(usuario));
        Usuario usuario7 =new Usuario("3");
        assertAll(() -> assertNotNull(usuario7), () -> assertEquals(HttpStatus.OK, usuario7)
        );
    }
       /**Usuario no encontrado en GET**/
   @Test
   public void usuarioNoEncontrado() {
       when(usuarioService.findById("J")).thenReturn(null);
       Usuario usuario8 =new Usuario("J");
       assertAll(() -> assertNotNull(usuario8), () -> assertEquals(HttpStatus.NOT_FOUND, usuario8)
       );
   }
    /**Post*/
    @Mock
    private List<String> imitaGuardar;
    @Test
    public void imitaUsuarioGuardado() {
        imitaGuardar.add("6");
        imitaGuardar.add("Diana");
        imitaGuardar.add("dr@gmail.com");
        imitaGuardar.add("26");
        imitaGuardar.add("55");
        assertNotEquals(null, save(imitaGuardar));
    }
    /**PUT*/
    @Mock
    private List<String> imitaActializa;
    @Test
    public void imitaUsuarioGuardado() {
        imitaActializa.add("6");
        imitaActializa.add("Lizbeth");
        imitaActializa.add("dr@gmail.com");
        imitaActializa.add("26");
        imitaActializa.add("55");
        assertNotEquals(null, updata(imitaGuardar));
    }

    /**Delete*/
    @ExtendWith(MockitoExtension.class)
    public class UsuarioController{
        @Mock
        private List<Usuario> imitaUsuarioEliminar;
        @InjectMocks
        public void eilinaUsuario(){
            Usuario usuario10 = new Usuario("10","Diana","dr@gmail.com",26,55);
            when(imitaUsuarioEliminar.deleteById("10")).thenReturn(null);
            assertEquals(null, imitaUsuarioEliminar)
        }
    }
    }

