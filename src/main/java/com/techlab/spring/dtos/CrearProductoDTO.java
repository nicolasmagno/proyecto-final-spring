package com.techlab.spring.dtos;

import lombok.Data;

@Data
public class CrearProductoDTO {
    private int id;
    private String nombre;
    private Double precio;
    private String descripcion;
    private String imagenUrl;
    private int stock;
    private int categoriaId;
}
