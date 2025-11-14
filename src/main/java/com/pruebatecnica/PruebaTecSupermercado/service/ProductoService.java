package com.pruebatecnica.PruebaTecSupermercado.service;

import com.pruebatecnica.PruebaTecSupermercado.model.Producto;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pruebatecnica.PruebaTecSupermercado.dto.ProductoDTO;
import com.pruebatecnica.PruebaTecSupermercado.exception.NotFoundException;
import com.pruebatecnica.PruebaTecSupermercado.mapper.Mapper;
import com.pruebatecnica.PruebaTecSupermercado.repository.ProductoRepository;


@Service
public class ProductoService implements IProductoService {
    @Autowired
    private ProductoRepository repo;
    @Override
    public List<ProductoDTO> obtenerProductos() {
        return repo.findAll().stream().map(Mapper::toDTO).toList();
    }

    @Override
    public ProductoDTO crearProducto(ProductoDTO productoDTO) {
        Producto prod = Producto.builder()
                .nombre(productoDTO.getNombre())
                .categoria(productoDTO.getCategoria())
                .precio(productoDTO.getPrecio())
                .cantidad(productoDTO.getCantidad())
                .build();
        return Mapper.toDTO(repo.save(prod));
    }

    @Override
    public ProductoDTO actualizarProducto(Long id, ProductoDTO productoDTO) {
        //Vamos a buscar si el producto existe 
        Producto prod = repo.findById(id)
        .orElseThrow(() -> new NotFoundException("Producto no encontrado"));
        prod.setNombre(productoDTO.getNombre());
        prod.setCategoria(productoDTO.getCategoria());
        prod.setPrecio(productoDTO.getPrecio());
        prod.setCantidad(productoDTO.getCantidad());
        return Mapper.toDTO(repo.save(prod));
    }

    @Override
    public void eliminarProducto(Long id) {
        
        if (!repo.existsById(id)) {
            throw new NotFoundException("Producto no encontrado para eliminar");
        }
        repo.deleteById(id);
    }
    
    
}
