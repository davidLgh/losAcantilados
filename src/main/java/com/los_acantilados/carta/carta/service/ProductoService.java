package com.los_acantilados.carta.carta.service;

import com.los_acantilados.carta.carta.dto.ProductoBasicDTO;
import com.los_acantilados.carta.carta.dto.ProductoDTO;

import java.util.List;

public interface ProductoService {
    List<ProductoDTO> getAllProductos(Long idCategoria);

    ProductoBasicDTO getProductoById(Long id);

    ProductoDTO save(ProductoDTO producto);

    ProductoDTO update(Long id, ProductoDTO producto);

    void delete(Long id);

    List<ProductoDTO> getAll();
}
