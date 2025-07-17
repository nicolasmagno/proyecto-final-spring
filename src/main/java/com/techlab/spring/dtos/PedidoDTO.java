package com.techlab.spring.dtos;

import com.techlab.spring.dtos.ItemPedidoDTO;
import lombok.Data;
import java.util.List;

@Data
public class PedidoDTO {
    private int usuarioId;
    private String estado;
    private List<ItemPedidoDTO> itemsPedido;
}
