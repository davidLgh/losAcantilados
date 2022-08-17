package com.los_acantilados.carta.carta.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "categorias")
@Getter
@Setter
public class CategoriaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String nombre;

    private String orden;

    private String visibilidad;

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "idCategoria")
    private List<ProductoEntity> productos;

}
