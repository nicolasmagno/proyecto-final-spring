package com.techlab.spring.excepcions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(StockInsuficienteException.class)
    public ResponseEntity<Object> handleStockInsuficienteException(StockInsuficienteException ex, WebRequest request) {

        Map<String, Object> body = new HashMap<>();
        body.put("mensaje", ex.getMessage());
        body.put("codigo", HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PedidoNoEncontradoException.class)
    public ResponseEntity<Object> handlePedidoNoEncontradoException(PedidoNoEncontradoException ex, WebRequest request) {

        Map<String, Object> body = new HashMap<>();
        body.put("mensaje", ex.getMessage());
        body.put("codigo", HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CategoriaNoEncontradaException.class)
    public ResponseEntity<Object> handleCategoriaNoEncontradaException(CategoriaNoEncontradaException ex, WebRequest request) {

        Map<String, Object> body = new HashMap<>();
        body.put("mensaje", ex.getMessage());
        body.put("codigo", HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ProductoNoEncontradoException.class)
    public ResponseEntity<Object> handleProductoNoEncontradoException(ProductoNoEncontradoException ex, WebRequest request) {

        Map<String, Object> body = new HashMap<>();
        body.put("mensaje", ex.getMessage());
        body.put("codigo", HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UsuarioNoEncontradoException.class)
    public ResponseEntity<Object> handleUsuarioNoEncontradoException(UsuarioNoEncontradoException ex, WebRequest request) {

        Map<String, Object> body = new HashMap<>();
        body.put("mensaje", ex.getMessage());
        body.put("codigo", HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

}
