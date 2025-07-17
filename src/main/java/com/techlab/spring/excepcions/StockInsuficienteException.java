package com.techlab.spring.excepcions;

public class StockInsuficienteException extends RuntimeException{
    public StockInsuficienteException(String mensaje) {
        super(mensaje);
    }
}
