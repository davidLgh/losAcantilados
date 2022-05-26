package com.los_acantilados.carta.carta.service;

import com.los_acantilados.carta.carta.dto.CategoriaDTO;

import java.util.List;


public interface CategoriaService {

    CategoriaDTO save(CategoriaDTO dto);

    List<CategoriaDTO> getAllCategorias();

    CategoriaDTO getCategoriaById(Long id);

    void delete(Long id);

    CategoriaDTO update(Long id, CategoriaDTO categoria);
}
