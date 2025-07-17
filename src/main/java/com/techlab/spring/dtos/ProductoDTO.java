package com.techlab.spring.dtos;

import com.techlab.spring.models.Categoria;
import lombok.Data;

@Data
public class ProductoDTO {
    private int id;
    private String nombre;
    private Double precio;
    private String descripcion;
    private String imagenUrl;
    private int stock;
    private Categoria Categoria;
}
