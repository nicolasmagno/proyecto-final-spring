package com.techlab.spring.services;

import com.techlab.spring.dtos.ProductoDTO;
import com.techlab.spring.models.Categoria;
import com.techlab.spring.repositories.CategoriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public List<Categoria> listarCategorias() {
        return categoriaRepository.findAll()
                .stream()
                .map(this::convertirACategoriaDTO)
                .collect(Collectors.toList());
    }

    private Categoria convertirACategoriaDTO(Categoria categoria) {
        Categoria dto = new Categoria();
        dto.setId(categoria.getId());
        dto.setNombre(categoria.getNombre());
        return dto;
    }

}
