package com.pruebatecnica.PruebaTecSupermercado.service;

import com.pruebatecnica.PruebaTecSupermercado.dto.VentaDTO;
import java.util.List;

public interface IVentaService {
    List<VentaDTO> obtenerVentas();
    VentaDTO crearVenta(VentaDTO ventaDTO);
    VentaDTO actualizarVenta(Long id, VentaDTO ventaDTO);
    void eliminarVenta(Long id);
    
}
