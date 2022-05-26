package com.los_acantilados.carta.carta.mapper;

import com.los_acantilados.carta.carta.dto.CategoriaDTO;
import com.los_acantilados.carta.carta.dto.ProductoBasicDTO;
import com.los_acantilados.carta.carta.dto.ProductoDTO;
import com.los_acantilados.carta.carta.entity.CategoriaEntity;
import com.los_acantilados.carta.carta.entity.ProductoEntity;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductoMapper {

    public ProductoDTO productoEntityToDto(ProductoEntity entity) {
        ProductoDTO dto = new ProductoDTO();
        dto.setId(entity.getId());
        dto.setNombre(entity.getNombre());
        dto.setImg(entity.getImg());
        dto.setDescripcion(entity.getDescripcion());
        dto.setPrecio(entity.getPrecio());
        dto.setIdCategoria(entity.getIdCategoria());
        return dto;
    }
    public ProductoBasicDTO productoEntityToBasicDto(ProductoEntity entity) {
        ProductoBasicDTO dto = new ProductoBasicDTO();
        dto.setImg(entity.getImg());
        return dto;
    }

    public List<ProductoDTO> etityListToDtoList(List<ProductoEntity> entities) {
        List <ProductoDTO> dtos = new ArrayList<>();
        for (ProductoEntity entity : entities){
            dtos.add(productoEntityToDto(entity));
        }
        return dtos;
    }


    public ProductoEntity productoDtoToEntity(ProductoDTO dto) {
        ProductoEntity entity = new ProductoEntity();
        entity.setNombre(dto.getNombre());
        entity.setDescripcion(dto.getDescripcion());
        entity.setImg(dto.getImg());
        entity.setPrecio(dto.getPrecio());
        entity.setIdCategoria(dto.getIdCategoria());
        return entity;
    }

    public ProductoEntity setProductoEntity(ProductoEntity entitySearched, ProductoDTO productoToUse) {
        entitySearched.setNombre(productoToUse.getNombre());
        entitySearched.setDescripcion(productoToUse.getDescripcion());
        entitySearched.setImg(productoToUse.getImg());
        entitySearched.setPrecio(productoToUse.getPrecio());
        entitySearched.setIdCategoria(productoToUse.getIdCategoria());
        return entitySearched;
    }
}
