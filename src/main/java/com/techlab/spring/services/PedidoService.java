package com.techlab.spring.services;

import com.techlab.spring.dtos.*;
import com.techlab.spring.excepcions.PedidoNoEncontradoException;
import com.techlab.spring.excepcions.ProductoNoEncontradoException;
import com.techlab.spring.excepcions.StockInsuficienteException;
import com.techlab.spring.excepcions.UsuarioNoEncontradoException;
import com.techlab.spring.models.*;
import com.techlab.spring.repositories.PedidoRepository;
import com.techlab.spring.repositories.ProductoRepository;
import com.techlab.spring.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ProductoRepository productoRepository;
    private final UsuarioRepository usuarioRepository;

    public PedidoDTO crearPedidoDesdeDTO(CrearPedidoDTO dto) {
        Pedido pedido = new Pedido();
        pedido.setUsuarioId(dto.getUsuarioId());
        pedido.setEstado("pendiente");
        pedido.setFecha(LocalDateTime.now());


        List<ItemPedido> items = dto.getItemsPedido().stream().map(itemDto -> {
            Producto producto = productoRepository.findById(itemDto.getProductoId())
                    .orElseThrow(() -> new ProductoNoEncontradoException("Producto no encontrado"));

            if (producto.getStock() < itemDto.getCantidad()) {
                throw new StockInsuficienteException("Stock insuficiente para este producto: " + producto.getNombre());
            }

            producto.setStock(producto.getStock() - itemDto.getCantidad());
            productoRepository.save(producto);

            ItemPedido item = new ItemPedido();
            item.setProductoId(itemDto.getProductoId());
            item.setCantidad(itemDto.getCantidad());
            item.setPedido(pedido);
            return item;
        }).collect(Collectors.toList());

        pedido.setItemsPedido(items);
        return convertirAPedidoDTO(pedidoRepository.save(pedido));
    }

    public PedidoDTO obtenerPedidoDTOPorId(int id) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new PedidoNoEncontradoException("Pedido no encontrado"));

        PedidoDTO dto = new PedidoDTO();
        dto.setUsuarioId(pedido.getUsuarioId());

        dto.setItemsPedido(
                pedido.getItemsPedido().stream().map(item -> {
                    var itemDto = new ItemPedidoDTO();
                    itemDto.setProductoId(item.getProductoId());
                    itemDto.setCantidad(item.getCantidad());
                    return itemDto;
                }).collect(Collectors.toList())
        );

        return dto;
    }

    public List<PedidoDetalleDTO> obtenerPedidosPorUsuario(int usuarioId) {

        if (!usuarioRepository.existsById(usuarioId)) {
            throw new UsuarioNoEncontradoException("Usuario con ID " + usuarioId + " no encontrado.");
        }else{
            System.out.println("Lo encontro: " + usuarioId);
        }

        List<Pedido> pedidos = pedidoRepository.findByUsuarioId(usuarioId);

        return pedidos.stream().map(pedido -> {
            PedidoDetalleDTO dto = new PedidoDetalleDTO();
            dto.setNumeroPedido(pedido.getId());
            dto.setFecha(pedido.getFecha());
            dto.setEstado(pedido.getEstado());

            List<ItemPedidoDetalleDTO> items = pedido.getItemsPedido().stream().map(item -> {
                Producto producto = productoRepository.findById(item.getProductoId())
                        .orElseThrow(() -> new ProductoNoEncontradoException("Producto no encontrado"));

                ItemPedidoDetalleDTO itemDto = new ItemPedidoDetalleDTO();
                itemDto.setProductoId(producto.getId());
                itemDto.setNombreProducto(producto.getNombre());
                itemDto.setPrecioUnitario(producto.getPrecio());
                itemDto.setCantidad(item.getCantidad());
                return itemDto;
            }).collect(Collectors.toList());

            dto.setItems(items);

            double total = items.stream()
                    .mapToDouble(i -> i.getPrecioUnitario() * i.getCantidad())
                    .sum();

            dto.setTotal(total);

            return dto;
        }).collect(Collectors.toList());
    }

    public List<Pedido> listarPedidos() {
        return pedidoRepository.findAll();
    }

    private PedidoDTO convertirAPedidoDTO(Pedido pedido) {
        PedidoDTO dto = new PedidoDTO();
        dto.setUsuarioId(pedido.getUsuarioId());
        dto.setEstado(pedido.getEstado());

        List<ItemPedidoDTO> itemsDTO = pedido.getItemsPedido().stream().map(item -> {
            ItemPedidoDTO itemDTO = new ItemPedidoDTO();
            itemDTO.setProductoId(item.getProductoId());
            itemDTO.setCantidad(item.getCantidad());
            return itemDTO;
        }).collect(Collectors.toList());

        dto.setItemsPedido(itemsDTO);
        return dto;
    }
}
