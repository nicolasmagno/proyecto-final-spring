package com.techlab.spring.controllers;

import com.techlab.spring.dtos.CrearUsuarioDTO;
import com.techlab.spring.dtos.PedidoDetalleDTO;
import com.techlab.spring.dtos.UsuarioDTO;
import com.techlab.spring.models.Usuario;
import com.techlab.spring.services.PedidoService;
import com.techlab.spring.services.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final PedidoService pedidoService;

    @GetMapping
    public List<UsuarioDTO> listarUsuarios() {
        return usuarioService.listarUsuarios();
    }

    @GetMapping("/{id}")
    public Usuario obtenerUsuario(@PathVariable int id) {
        return usuarioService.obtenerUsuarioPorId(id);
    }

    @PostMapping
    public UsuarioDTO crearUsuario(@RequestBody CrearUsuarioDTO usuario) {
        return usuarioService.crearUsuario(usuario);
    }

    @GetMapping("/{id}/pedidos")
    public List<PedidoDetalleDTO> obtenerPedidos(@PathVariable int id) {
        return pedidoService.obtenerPedidosPorUsuario(id);
    }
}
