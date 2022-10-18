package com.wizeline.proyecto.controller;

import com.wizeline.proyecto.model.UserRT;
import com.wizeline.proyecto.model.Usuario;
import com.wizeline.proyecto.service.UserService;
import com.wizeline.proyecto.service.UsuarioService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class UserControllerTest {
    /**GET all*/
    @Mock
    private UserService userService;
    @InjectMocks
    private UserControllerTest userController;
    @Test
    public void traesUser(){
        List<UserRT> usuario0=List.of(new UserRT("61","Diana","Ranggel","dr@gmail.com"), new Usuario("62","Lizbeth","Soto","lr@gmail.com"));
        when(userService.getUsers()).thenReturn(usuario0);
        List<UserRT> usuario1 = userController.getUsers();
        assertEquals(usuario0, usuario1);
    }

    /**Post*/
    @Mock
    private List<String> imitaGuardar;
    @Test
    public void imitaUserGuardado() {
        imitaGuardar.add("63");
        imitaGuardar.add("Diana");
        imitaGuardar.add("Soto");
        imitaGuardar.add("dr63@gmail.com");
        assertNotEquals(null, saveUser(imitaGuardar));
    }
    /**PUT*/
    @Mock
    private List<String> imitaActializa;
    @Test
    public void imitaUserGuardado() {
        imitaGuardar.add("63");
        imitaGuardar.add("Lizbeth");
        imitaGuardar.add("Soto");
        imitaGuardar.add("dr63@gmail.com");
        assertNotEquals(null, updateUser(imitaGuardar));
    }

    /**Delete*/
    @ExtendWith(MockitoExtension.class)
    public class UserController{
        @Mock
        private List<Usuario> imitaUserEliminar;
        @InjectMocks
        public void eilinaUser(){
            UserRT usuario10 = new UserRT("63","Diana","Ranggel","dr@gmail.com");
            when(imitaUserEliminar.deleteUser("63")).thenReturn(null);
            assertEquals(null, imitaUserEliminar)
        }
    }
}