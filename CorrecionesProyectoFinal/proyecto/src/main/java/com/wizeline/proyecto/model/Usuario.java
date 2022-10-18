package com.wizeline.proyecto.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "Usuario")
public class Usuario {
    @Id
    private String id;
    private String nombre;
    private String email;
    private Integer edad;
    private Integer telefono;
    private String ERROR;

    public Usuario(String id,String nombre, String email, Integer edad, Integer telefono ) {
        this.id = id;
        this.nombre = nombre;
        this.email=email;
        this.edad=edad;
        this.telefono=telefono;
    }

    public Usuario(String id) {
        this.id = id;
    }

    public Usuario(Integer edad) {
        this.edad = edad;
    }

    public Usuario(String nombre, String email) {
        this.nombre = nombre;
        this.email=email;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public Integer getTelefono() {
        return telefono;
    }

    public void setTelefono(Integer telefono) {
        this.telefono = telefono;
    }

    public void getStatusCode() {this.ERROR=ERROR;}

    //Para la pruebas unitarias
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Usuario)) {
            return false;
        }

        Usuario otro = (Usuario) obj;
        return this.nombre != null && this.email!= null && this.nombre.equals(otro.nombre)
                && this.email.equals(otro.email);
    }
}
