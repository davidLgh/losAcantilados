package com.los_acantilados.carta.carta.controller;

import com.los_acantilados.carta.carta.dto.CategoriaDTO;
import com.los_acantilados.carta.carta.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    //DEVOLVER TODAS LAS CATEGORIAS
    @GetMapping("/home")
    public ResponseEntity<List<CategoriaDTO>> getAllHome(){
        List<CategoriaDTO> categorias = categoriaService.getAllCategorias();
        return ResponseEntity.ok().body(categorias);
    }
   //DEVOLVER TODAS LAS CATEGORIAS
    @GetMapping
    public ResponseEntity<List<CategoriaDTO>> getAll(){
        List<CategoriaDTO> categorias = categoriaService.getAllCategorias();
        return ResponseEntity.ok().body(categorias);
    }

    //DEVOLVER CATEGORIA POR ID
    @GetMapping("/{id}")
    public ResponseEntity<CategoriaDTO> getCategoria(@PathVariable Long id){
    CategoriaDTO categoria = categoriaService.getCategoriaById(id);
        return ResponseEntity.ok().body(categoria);
    }


    //CREAR CATEGORIA (cuando la creo no aosocio productos)
    @PostMapping
    public ResponseEntity<CategoriaDTO> save(@RequestBody CategoriaDTO categoria) {
        CategoriaDTO categoriaCreated = categoriaService.save(categoria);
        return ResponseEntity.status(HttpStatus.CREATED).body(categoriaCreated);
    }

    //ELIMINAR CATEGORIA
    @DeleteMapping ("/{id}")
    public ResponseEntity<Void> delete (@PathVariable Long id){
        categoriaService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaDTO> update (@PathVariable Long id,@RequestBody CategoriaDTO categoria){
        CategoriaDTO result = categoriaService.update(id, categoria);
        return ResponseEntity.ok().body(result);
    }



}