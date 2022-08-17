package com.los_acantilados.carta.carta.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CategoriaDTO {

    private Long id;

    private String nombre;

    private String orden;

    private String visibilidad;
}
