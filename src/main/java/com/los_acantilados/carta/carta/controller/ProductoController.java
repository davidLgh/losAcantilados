package com.los_acantilados.carta.carta.controller;
import com.los_acantilados.carta.carta.dto.ProductoBasicDTO;
import com.los_acantilados.carta.carta.dto.ProductoDTO;
import com.los_acantilados.carta.carta.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("productos")
public class ProductoController {

    @Autowired
    CategoriaController categoriaController;
    @Autowired
    private ProductoService productoService;

    @GetMapping("/home")
    public ResponseEntity<List<ProductoDTO>> getAllHome(){
        List<ProductoDTO> productos = productoService.getAll();
        return ResponseEntity.ok().body(productos);
    }
    //DEVUELVE TODOS LOS PRODUCTOS
    @GetMapping
    public ResponseEntity<List<ProductoDTO>> getAll(){
        List<ProductoDTO> productos = productoService.getAll();
        return ResponseEntity.ok().body(productos);
    }

    //DEVUELVE PRODUCTO POR ID - Solo devuelve imagen
    @GetMapping("/{id}")
    public ResponseEntity<ProductoBasicDTO> getProducto(@PathVariable Long id){
        ProductoBasicDTO producto = productoService.getProductoById(id);
        return ResponseEntity.ok().body(producto);
    }

    //DEVOLVER TODOS LOS PRODUCTOS - por ID CATEGORIA
    @GetMapping("/categoria/{idCategoria}")
    public ResponseEntity<List<ProductoDTO>> getAll(@PathVariable Long idCategoria){
        List<ProductoDTO> productos = productoService.getAllProductos(idCategoria);
        return ResponseEntity.ok().body(productos);
    }
    //CREAR PRODUCTO (asocio categoria cuando lo creo)
    @PostMapping
    public ResponseEntity<ProductoDTO> save(@RequestBody ProductoDTO producto) {
        ProductoDTO productoCreated = productoService.save(producto);
        return ResponseEntity.status(HttpStatus.CREATED).body(productoCreated);
    }
    //MODIFICAR PRODUCTO
    @PutMapping("/{id}")
    public ResponseEntity<ProductoDTO> update (@PathVariable Long id,@RequestBody ProductoDTO producto){
        ProductoDTO result = productoService.update(id, producto);
        return ResponseEntity.ok().body(result);
    }

    //ELIMINAR PRODUCTO
    @DeleteMapping ("/{id}")
    public ResponseEntity<Void> delete (@PathVariable Long id){
        productoService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
