package com.pruebatecnica.PruebaTecSupermercado.mapper;

import com.pruebatecnica.PruebaTecSupermercado.dto.DetalleVentaDTO;
import com.pruebatecnica.PruebaTecSupermercado.dto.ProductoDTO;
import com.pruebatecnica.PruebaTecSupermercado.dto.VentaDTO;
import com.pruebatecnica.PruebaTecSupermercado.dto.SucursalDTO;
import com.pruebatecnica.PruebaTecSupermercado.model.Producto;
import com.pruebatecnica.PruebaTecSupermercado.model.Venta;
import com.pruebatecnica.PruebaTecSupermercado.model.Sucursal;


import java.util.stream.Collectors;



public class Mapper {
    
    // Mapeo de Producto a ProductoDTO
    public static ProductoDTO toDTO(Producto p) {
        if(p == null) return null;

        return ProductoDTO.builder()
                .id(p.getId())
                .nombre(p.getNombre())
                .categoria(p.getCategoria())
                .precio(p.getPrecio())
                .cantidad(p.getCantidad())
                .build();
    }

    // Mapeo de Venta a VentaDTO
    public static VentaDTO toDTO(Venta venta) {
        if(venta == null) return null;

        java.util.List<DetalleVentaDTO> detalle = venta.getDetalle().stream().map(det ->
            DetalleVentaDTO.builder()
                .id(det.getProducto().getId())
                .nombreProd(det.getProducto().getNombre())
                .cantProd(det.getCantProd())
                .precio(det.getPrecioUnitario())
                .subtotal(det.getPrecioUnitario() * det.getCantProd())
                .build()
        ).collect(Collectors.toList());

        Double total = detalle.stream()
                .map(DetalleVentaDTO::getSubtotal)
                .reduce(0.0, Double::sum);

        return VentaDTO.builder()
                .id(venta.getId())
                .fecha(venta.getFecha())
                .estado(venta.getEstado())
                .idSucursal(venta.getSucursal().getId())
                .detalle(detalle)
                .total(total)
                .build();
    }

    // Mapeo de Sucursal a SucursalDTO
     public static SucursalDTO toDTO(Sucursal s) {
        if(s == null) return null;

        return SucursalDTO.builder()
                .id(s.getId())
                .nombre(s.getNombre())
                .direccion(s.getDireccion())
                .build();
    }   

}
