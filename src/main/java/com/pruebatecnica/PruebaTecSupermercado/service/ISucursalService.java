package com.pruebatecnica.PruebaTecSupermercado.service;

import com.pruebatecnica.PruebaTecSupermercado.dto.SucursalDTO;


import java.util.List;

public interface ISucursalService {
    List<SucursalDTO> obtenerSucursales();
    SucursalDTO crearSucursal(SucursalDTO sucursalDTO);
    SucursalDTO actualizarSucursal(Long id, SucursalDTO sucursalDTO);
    void eliminarSucursal(Long id);

}
