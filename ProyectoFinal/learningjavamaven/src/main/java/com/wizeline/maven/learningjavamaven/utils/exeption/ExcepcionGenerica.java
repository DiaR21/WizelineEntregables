package com.wizeline.maven.learningjavamaven.utils.exeption;


public class ExcepcionGenerica extends RuntimeException {
    public ExcepcionGenerica(String mensajeError) {
        super(mensajeError);
    }
}