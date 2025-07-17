package com.techlab.spring.excepcions;

public class PedidoNoEncontradoException extends RuntimeException{
    public PedidoNoEncontradoException(String mensaje){
        super(mensaje);
    }
}
