package com.techlab.spring.excepcions;

public class UsuarioNoEncontradoException extends RuntimeException{
    public UsuarioNoEncontradoException(String mensaje){
        super(mensaje);
    }
}
