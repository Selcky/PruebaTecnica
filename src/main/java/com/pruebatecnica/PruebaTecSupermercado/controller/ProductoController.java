package com.pruebatecnica.PruebaTecSupermercado.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.pruebatecnica.PruebaTecSupermercado.service.IProductoService;
import org.springframework.web.bind.annotation.RequestBody;
import com.pruebatecnica.PruebaTecSupermercado.dto.ProductoDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import java.net.URI;
import java.util.List;

@Controller
@RequestMapping("/api/productos")

public class ProductoController {
    
    @Autowired
    private IProductoService productoService;

    @GetMapping
    public ResponseEntity<List<ProductoDTO>> obtenerProductos() {

        return ResponseEntity.ok(productoService.obtenerProductos());
    }

    @PostMapping
    public ResponseEntity<ProductoDTO> crearProducto(@RequestBody ProductoDTO dto) {
        ProductoDTO creado = productoService.crearProducto(dto);
        return ResponseEntity.created(URI.create("/api/productos/" + creado.getId())).body(creado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductoDTO> actualizarProducto(@PathVariable Long id, @RequestBody ProductoDTO dto) {
        return ResponseEntity.ok(productoService.actualizarProducto(id, dto));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long id) {
        productoService.eliminarProducto(id);
        return ResponseEntity.noContent().build();
    }

}
