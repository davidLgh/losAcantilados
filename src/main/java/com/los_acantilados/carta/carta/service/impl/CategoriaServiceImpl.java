package com.los_acantilados.carta.carta.service.impl;

import com.los_acantilados.carta.carta.dto.CategoriaDTO;
import com.los_acantilados.carta.carta.entity.CategoriaEntity;
import com.los_acantilados.carta.carta.mapper.CategoriaMapper;
import com.los_acantilados.carta.carta.repository.CategoriaRepository;
import com.los_acantilados.carta.carta.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaServiceImpl implements CategoriaService {

    @Autowired
    private CategoriaMapper categoriaMapper;
    @Autowired
    private CategoriaRepository categoriaRepository;

    public CategoriaDTO save(CategoriaDTO dto){
        CategoriaEntity entity = categoriaMapper.categoriaDtoToEntity(dto);
        CategoriaEntity entitySaved = categoriaRepository.save(entity);
        CategoriaDTO result = categoriaMapper.categoriaEntityToDto(entitySaved);
        return result;
    }


    public List<CategoriaDTO> getAllCategorias() {
        List<CategoriaEntity> entities = categoriaRepository.findAll();
        List<CategoriaDTO> result = categoriaMapper.categoriaEntityListToDtoList(entities);
        return result;
    }

    public CategoriaDTO getCategoriaById(Long id) {
        Optional<CategoriaEntity> optional = categoriaRepository.findById(id);
        CategoriaEntity entity = optional.get();
        CategoriaDTO dto = categoriaMapper.categoriaEntityToDto(entity);
        return dto;
    }

    public void delete(Long id) {
        categoriaRepository.deleteById(id);
    }


    public CategoriaDTO update(Long id, CategoriaDTO dtoToUse) {
        Optional<CategoriaEntity> optional = categoriaRepository.findById(id);
        CategoriaEntity entitySearched = optional.get();
        CategoriaEntity entityUpdated = categoriaMapper.setCategoriaEntity(entitySearched, dtoToUse);
        CategoriaEntity entitySaved = categoriaRepository.save(entityUpdated);
        CategoriaDTO result = categoriaMapper.categoriaEntityToDto(entitySaved);
        return result;
    }
}
