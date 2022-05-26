package com.los_acantilados.carta.carta.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProductoDTO {

    private Long id;
    private String nombre;
    private String descripcion;
    private float precio; //$$
    private String img;
    private Long idCategoria;

//    private List <AlegenoDTO> alergenos;



}
