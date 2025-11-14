package com.pruebatecnica.PruebaTecSupermercado.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import com.pruebatecnica.PruebaTecSupermercado.service.IVentaService;

import com.pruebatecnica.PruebaTecSupermercado.dto.VentaDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import java.net.URI;
import java.util.List;
    
@Controller
@RequestMapping("/api/ventas")
public class VentaController {

    @Autowired
    private IVentaService ventaService;

    @GetMapping
    public ResponseEntity<List<VentaDTO>> obtenerVentas() {
        return ResponseEntity.ok(ventaService.obtenerVentas());
    }

    @PutMapping("/{id}")
    public ResponseEntity<VentaDTO> actualizarVenta(@PathVariable Long id, @RequestBody VentaDTO dto) {
        return ResponseEntity.ok(ventaService.actualizarVenta(id, dto));
        
    }

    /**
     * Crear una nueva venta usando directamente VentaDTO en la request (opcion simple, sin request separado).
     * Se espera que el DTO traiga la informacion necesaria para crear la venta.
     */
    @PostMapping
    public ResponseEntity<VentaDTO> crearVenta(@RequestBody VentaDTO dto) {
        VentaDTO creado = ventaService.crearVenta(dto);
        return ResponseEntity.created(URI.create("/api/ventas/" + creado.getId())).body(creado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarVenta(@PathVariable Long id) {
        ventaService.eliminarVenta(id);
        return ResponseEntity.noContent().build();
    }


}
