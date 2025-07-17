package com.techlab.spring.excepcions;

public class CategoriaNoEncontradaException extends RuntimeException{
    public CategoriaNoEncontradaException(String mensaje){
        super(mensaje);
    }
}
