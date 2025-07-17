package com.techlab.spring.controllers;

import com.techlab.spring.dtos.CrearPedidoDTO;
import com.techlab.spring.dtos.PedidoDTO;
import com.techlab.spring.models.Pedido;
import com.techlab.spring.services.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
@RequiredArgsConstructor
public class PedidoController {

    private final PedidoService pedidoService;

    @PostMapping
    public PedidoDTO crearPedido(@RequestBody CrearPedidoDTO pedidoDTO) {
        return pedidoService.crearPedidoDesdeDTO(pedidoDTO);
    }

    @GetMapping("/{id}")
    public PedidoDTO obtenerPedido(@PathVariable int id) {
        return pedidoService.obtenerPedidoDTOPorId(id);
    }


}
