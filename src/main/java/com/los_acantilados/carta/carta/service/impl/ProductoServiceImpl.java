package com.los_acantilados.carta.carta.service.impl;

import com.los_acantilados.carta.carta.dto.ProductoBasicDTO;
import com.los_acantilados.carta.carta.dto.ProductoDTO;
import com.los_acantilados.carta.carta.entity.ProductoEntity;
import com.los_acantilados.carta.carta.mapper.ProductoMapper;
import com.los_acantilados.carta.carta.repository.ProductoRepository;
import com.los_acantilados.carta.carta.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoServiceImpl implements ProductoService {
    @Autowired
    ProductoRepository productoRepository;
    @Autowired
    ProductoMapper productoMapper;

    public ProductoBasicDTO getProductoById(Long id) {
        Optional<ProductoEntity> optional = productoRepository.findById(id);
        ProductoEntity entity = optional.get();
        ProductoBasicDTO dto = productoMapper.productoEntityToBasicDto(entity);
        return dto;
    }

    public List<ProductoDTO> getAllProductos(Long idCategoria) {
        List<ProductoEntity> entities = productoRepository.findByCategoriaId(idCategoria);
        List<ProductoDTO> dtos = productoMapper.etityListToDtoList(entities);
        return dtos;
    }

    public ProductoDTO save(ProductoDTO dto) {
        ProductoEntity entity = productoMapper.productoDtoToEntity(dto);
        ProductoEntity entitySaved = productoRepository.save(entity);
        ProductoDTO result = productoMapper.productoEntityToDto(entitySaved);
        return result;
    }

    public ProductoDTO update(Long id, ProductoDTO productoToUse) {
        Optional<ProductoEntity> optional = productoRepository.findById(id);
        ProductoEntity entitySearched = optional.get();
        ProductoEntity entityUpdated = productoMapper.setProductoEntity(entitySearched, productoToUse);
        ProductoEntity entitySaved = productoRepository.save(entityUpdated);
        ProductoDTO result = productoMapper.productoEntityToDto(entitySaved);
        return result;
    }

    public void delete(Long id) {
        productoRepository.deleteById(id);
    }

    public List<ProductoDTO> getAll() {
        List<ProductoEntity> entities = productoRepository.findAll();
        List<ProductoDTO> dtos = productoMapper.etityListToDtoList(entities);
        return dtos;
    }

}
