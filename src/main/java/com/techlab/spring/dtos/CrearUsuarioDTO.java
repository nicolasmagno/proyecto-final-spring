package com.techlab.spring.dtos;

import lombok.Data;

@Data
public class CrearUsuarioDTO {
    private String nombre;
    private String email;
    private String password;
}
