package com.los_acantilados.carta.carta.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "alergenos")
@Getter
@Setter
@SQLDelete(sql= "UPDATE alergenos SET deleted =true WHERE id=?")
@Where(clause = "deleted =false")
public class AlergenoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String img;

    private String nombre;

    @ManyToMany (mappedBy = "alergenos", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<ProductoEntity> productos = new HashSet<>();

    private boolean deleted = Boolean.FALSE;

}
