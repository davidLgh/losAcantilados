package com.los_acantilados.carta.carta.repository;

import com.los_acantilados.carta.carta.entity.ProductoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProductoRepository extends JpaRepository <ProductoEntity, Long>{

    @Query(value = "SELECT * FROM productos p WHERE p.id_categoria = ?1", nativeQuery = true)
    List<ProductoEntity> findByCategoriaId(Long id);

}
