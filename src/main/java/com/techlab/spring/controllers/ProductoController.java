package com.techlab.spring.controllers;

import com.techlab.spring.dtos.CrearProductoDTO;
import com.techlab.spring.dtos.ProductoDTO;
import com.techlab.spring.models.Producto;
import com.techlab.spring.services.ProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
@RequiredArgsConstructor
public class ProductoController {

    private final ProductoService productoService;

    @GetMapping
    public List<ProductoDTO> listarProductos() {
        return productoService.listarProductos();
    }

    @GetMapping("/{id}")
    public ProductoDTO obtenerProducto(@PathVariable int id) {
        return productoService.obtenerProductoDTOPorId(id);
    }

    @PostMapping
    public ProductoDTO crearProducto(@RequestBody CrearProductoDTO producto) {
        return productoService.guardarProducto(producto);
    }

    @PutMapping("/{id}")
    public ProductoDTO actualizarProducto(@PathVariable int id, @RequestBody Producto producto) {
        return productoService.actualizarProducto(id, producto);
    }

    @DeleteMapping("/{id}")
    public void eliminarProducto(@PathVariable int id) {
        productoService.eliminarProducto(id);
    }
}
