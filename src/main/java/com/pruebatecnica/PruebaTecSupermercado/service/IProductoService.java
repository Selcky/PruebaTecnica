package com.pruebatecnica.PruebaTecSupermercado.service;
import com.pruebatecnica.PruebaTecSupermercado.dto.ProductoDTO;
import java.util.List;

public interface IProductoService {
    List<ProductoDTO> obtenerProductos();
    ProductoDTO crearProducto(ProductoDTO productoDTO);
    ProductoDTO actualizarProducto(Long id, ProductoDTO productoDTO);
    void eliminarProducto(Long id);
}
