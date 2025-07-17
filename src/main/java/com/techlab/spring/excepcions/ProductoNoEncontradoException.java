package com.techlab.spring.excepcions;

public class ProductoNoEncontradoException extends RuntimeException{
    public ProductoNoEncontradoException(String mensaje) {
        super(mensaje);
    }
}
