package com.los_acantilados.carta.carta.mapper;


import com.los_acantilados.carta.carta.dto.CategoriaDTO;
import com.los_acantilados.carta.carta.entity.CategoriaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CategoriaMapper {


    public CategoriaEntity categoriaDtoToEntity(CategoriaDTO dto){
        CategoriaEntity entity = new CategoriaEntity();
        entity.setNombre(dto.getNombre());
        return entity;
    }
    public CategoriaDTO categoriaEntityToDto(CategoriaEntity entity){
        CategoriaDTO dto = new CategoriaDTO();
        dto.setNombre(entity.getNombre());
        dto.setOrden(entity.getOrden());
        dto.setVisibilidad(entity.getVisibilidad());
        dto.setId(entity.getId());
        return dto;
    }
    public List<CategoriaDTO> categoriaEntityListToDtoList(List<CategoriaEntity> entities) {
        List<CategoriaDTO> dtos = new ArrayList<>();
        for (CategoriaEntity entity : entities) {
            dtos.add(this.categoriaEntityToDto(entity));
        }
        return dtos;
    }

    public CategoriaEntity setCategoriaEntity(CategoriaEntity entitySearched, CategoriaDTO dtoToUse) {
        entitySearched.setNombre(dtoToUse.getNombre());
        entitySearched.setOrden(dtoToUse.getOrden());
        entitySearched.setVisibilidad(dtoToUse.getVisibilidad());
        return entitySearched;
    }








 /*   INTENTANDO TRAER CATEGORIA CON PRODUCTOS ASOCIADOS

    public CategoriaDTO categoriaIdEntityToDto(CategoriaEntity entity){
        CategoriaDTO dto = new CategoriaDTO();
        dto.setNombre(entity.getNombre());
        dto.setId(entity.getId());
        // TODO: 3/5/2022 setear en el DTO la lista que traigo de la entidad "productos" 
        dto.setProdutosDeCategoria(entity.getProductos(categoriaMapper.categoriaEntityListToDtoList()));


        return dto;
    }
*/


}
