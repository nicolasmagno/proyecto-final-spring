package com.techlab.spring.dtos;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class PedidoDetalleDTO {
    private int numeroPedido;
    private LocalDateTime fecha;
    private String estado;
    private double total;
    private List<ItemPedidoDetalleDTO> items;
}
