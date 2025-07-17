package com.techlab.spring.dtos;

import lombok.Data;

import java.util.List;

@Data
public class CrearPedidoDTO {
    private int usuarioId;
    private List<ItemPedidoDTO> itemsPedido;
}
