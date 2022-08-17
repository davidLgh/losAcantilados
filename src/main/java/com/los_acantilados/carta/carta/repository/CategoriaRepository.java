package com.los_acantilados.carta.carta.repository;

import com.los_acantilados.carta.carta.entity.CategoriaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoriaRepository extends JpaRepository<CategoriaEntity, Long> {

    @Query(value = "SELECT * FROM categorias ORDER BY orden", nativeQuery = true)
    List<CategoriaEntity> findAll();
}
