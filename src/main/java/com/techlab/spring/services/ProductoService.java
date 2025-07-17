package com.techlab.spring.services;

import com.techlab.spring.dtos.CrearProductoDTO;
import com.techlab.spring.dtos.ProductoDTO;
import com.techlab.spring.excepcions.CategoriaNoEncontradaException;
import com.techlab.spring.excepcions.ProductoNoEncontradoException;
import com.techlab.spring.models.Categoria;
import com.techlab.spring.models.Producto;
import com.techlab.spring.repositories.CategoriaRepository;
import com.techlab.spring.repositories.ProductoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductoService {

    private final ProductoRepository productoRepository;
    private final CategoriaRepository categoriaRepository;

    public List<ProductoDTO> listarProductos() {
        return productoRepository.findAll().stream()
                .map(this::convertirAProductoDTO)
                .collect(Collectors.toList());
    }


    public ProductoDTO obtenerProductoDTOPorId(int id) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new ProductoNoEncontradoException("Producto no encontrado"));

        return convertirAProductoDTO(producto);
    }

    public ProductoDTO guardarProducto(CrearProductoDTO dto) {
        Categoria categoria = categoriaRepository.findById(dto.getCategoriaId())
                .orElseThrow(() -> new CategoriaNoEncontradaException("Categoría no encontrada"));

        Producto producto = new Producto();
        producto.setNombre(dto.getNombre());
        producto.setDescripcion(dto.getDescripcion());
        producto.setPrecio(dto.getPrecio());
        producto.setStock(dto.getStock());
        producto.setImagenUrl(dto.getImagenUrl());
        producto.setCategoria(categoria);

        return convertirAProductoDTO(productoRepository.save(producto));
    }

    public Producto obtenerProductoPorId(int id) {
        return productoRepository.findById(id)
                .orElseThrow(() -> new ProductoNoEncontradoException("Producto no encontrado"));
    }

    public ProductoDTO actualizarProducto(int id, Producto producto) {
        Producto prod = obtenerProductoPorId(id);
        prod.setNombre(producto.getNombre());
        prod.setPrecio(producto.getPrecio());
        prod.setStock(producto.getStock());
        prod.setDescripcion(producto.getDescripcion());
        return convertirAProductoDTO(productoRepository.save(prod));
    }

    public void eliminarProducto(int id) {
        productoRepository.deleteById(id);
    }

    public ProductoDTO convertirAProductoDTO(Producto producto) {
        ProductoDTO dto = new ProductoDTO();
        dto.setId(producto.getId());
        dto.setNombre(producto.getNombre());
        dto.setPrecio(producto.getPrecio());
        dto.setImagenUrl(producto.getImagenUrl());
        dto.setStock(producto.getStock());
        dto.setDescripcion(producto.getDescripcion());
        dto.setCategoria(producto.getCategoria());
        return dto;
    }
}
