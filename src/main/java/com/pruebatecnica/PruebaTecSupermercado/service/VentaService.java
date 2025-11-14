package com.pruebatecnica.PruebaTecSupermercado.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pruebatecnica.PruebaTecSupermercado.dto.VentaDTO;
import com.pruebatecnica.PruebaTecSupermercado.exception.NotFoundException;
import com.pruebatecnica.PruebaTecSupermercado.repository.VentaRepository;
import com.pruebatecnica.PruebaTecSupermercado.repository.ProductoRepository;
import com.pruebatecnica.PruebaTecSupermercado.repository.SucursalRepository;
import com.pruebatecnica.PruebaTecSupermercado.model.Venta;
import java.util.ArrayList;
import com.pruebatecnica.PruebaTecSupermercado.mapper.Mapper;
import com.pruebatecnica.PruebaTecSupermercado.model.Sucursal;
import com.pruebatecnica.PruebaTecSupermercado.model.DetalleVenta;
import com.pruebatecnica.PruebaTecSupermercado.dto.DetalleVentaDTO;
import com.pruebatecnica.PruebaTecSupermercado.model.Producto;



@Service
public class VentaService implements IVentaService {
    @Autowired
    private VentaRepository VentaRepo;
    @Autowired
    private ProductoRepository productoRepo;
    @Autowired
    private SucursalRepository sucursalRepo;

    @Override
    public List<VentaDTO> obtenerVentas() {

        List<Venta> ventas = VentaRepo.findAll();
        List<VentaDTO> ventasDTO = new ArrayList<>();
        VentaDTO dto;
        for (Venta v : ventas) {
             dto = Mapper.toDTO(v);
             ventasDTO.add(dto);
            
        }   
        return ventasDTO;
    }

    @Override
    public VentaDTO crearVenta(VentaDTO ventaDTO) {

        //Validaciones
        if(ventaDTO == null) throw new RuntimeException("La venta no puede ser nula");
        if(ventaDTO.getIdSucursal() == null) throw new RuntimeException("Debe Indicar la sucursal de la venta");
        if (ventaDTO.getDetalle() == null || ventaDTO.getDetalle().isEmpty()) {
            throw new RuntimeException("La venta debe tener al menos un detalle");
        }

        //Buscar la sucursal
        Sucursal suc = sucursalRepo.findById(ventaDTO.getIdSucursal())
        .orElse(null);
        if (suc == null) {
            throw new NotFoundException("Sucursal no encontrada para la venta");
        }

        //Crear la venta
        Venta vent = new Venta();
        vent.setFecha(ventaDTO.getFecha());
        vent.setEstado(ventaDTO.getEstado());
        vent.setSucursal(suc);
        vent.setTotal(ventaDTO.getTotal());

        //Lista de detalles de venta
        List<DetalleVenta> detalles = new ArrayList<>();
        Double totalCalc = 0.0;
        for (DetalleVentaDTO detDTO : ventaDTO.getDetalle()) {
            //Buscar por el nombre del producto
            Producto p = productoRepo.findByNombre(detDTO.getNombreProd()).orElse(null);
            if (p == null) {
                throw new NotFoundException("Producto no encontrado: " + detDTO.getNombreProd());
            }

            //Crear el detalle de venta
            DetalleVenta detalleVent = new DetalleVenta();
            detalleVent.setProducto(p);
            detalleVent.setCantProd(detDTO.getCantProd());
            detalleVent.setPrecioUnitario(detDTO.getPrecio());
            detalleVent.setVenta(vent);
            detalles.add(detalleVent);
            totalCalc = totalCalc+(detDTO.getPrecio() * detDTO.getCantProd());
        }

        //Settear la lista de los detalles de la venta
        vent.setDetalle(detalles);

        //Guardar la venta en la BD
        vent = VentaRepo.save(vent);

        //Mappear a DTO y retornarlo
        VentaDTO ventaSalida = Mapper.toDTO(vent);
        return ventaSalida;
        
    }

    @Override
    public VentaDTO actualizarVenta(Long id, VentaDTO ventaDTO) {
        //Buscar la venta a actualizar
        Venta v = VentaRepo.findById(id).orElse(null);
        if (v == null) throw new NotFoundException("Venta no encontrada para actualizar");
        
        if(ventaDTO.getFecha() != null) {
            v.setFecha(ventaDTO.getFecha());
        }
        if(ventaDTO.getEstado() != null) {
            v.setEstado(ventaDTO.getEstado());
        }
        if(ventaDTO.getTotal() != null) {
            v.setTotal(ventaDTO.getTotal());
        }
        if(ventaDTO.getIdSucursal() != null) {
            Sucursal suc = sucursalRepo.findById(ventaDTO.getIdSucursal()).orElse(null);
            if (suc == null) throw new NotFoundException("Sucursal no encontrada para la venta");
            v.setSucursal(suc);
        }

        VentaRepo.save(v); //Actualizar la venta

        VentaDTO ventaSalida = Mapper.toDTO(v);
        return ventaSalida;

    }

    @Override
    public void eliminarVenta(Long id) {

        Venta v = VentaRepo.findById(id).orElse(null);
        if (v == null) throw new NotFoundException("Venta no encontrada para eliminar");

        VentaRepo.deleteById(id);
    }

}
