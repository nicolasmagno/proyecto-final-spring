package com.techlab.spring.dtos;

import lombok.Data;

@Data
public class ItemPedidoDetalleDTO {
    private int productoId;
    private String nombreProducto;
    private double precioUnitario;
    private int cantidad;
}
