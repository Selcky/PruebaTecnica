package com.pruebatecnica.PruebaTecSupermercado.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pruebatecnica.PruebaTecSupermercado.repository.SucursalRepository;

import com.pruebatecnica.PruebaTecSupermercado.dto.SucursalDTO;
import com.pruebatecnica.PruebaTecSupermercado.mapper.Mapper;
import com.pruebatecnica.PruebaTecSupermercado.model.Sucursal;
import com.pruebatecnica.PruebaTecSupermercado.exception.NotFoundException;

@Service
public class SucursalService implements ISucursalService {

    @Autowired
    private SucursalRepository repo;

    @Override
    public List<SucursalDTO> obtenerSucursales() {
        return repo.findAll()
        .stream()
        .map(Mapper::toDTO)
        .toList();
    }

    @Override
    public SucursalDTO crearSucursal(SucursalDTO sucursalDTO) {
        Sucursal suc = Sucursal.builder()
                .nombre(sucursalDTO.getNombre())
                .direccion(sucursalDTO.getDireccion())
                .build();
        return Mapper.toDTO(repo.save(suc));
    }

    @Override
    public SucursalDTO actualizarSucursal(Long id, SucursalDTO sucursalDTO) {
        Sucursal suc = repo.findById(id)
        .orElseThrow(() -> new NotFoundException("Sucursal no encontrada para actualizar"));
        suc.setNombre(sucursalDTO.getNombre());
        suc.setDireccion(sucursalDTO.getDireccion());
        return Mapper.toDTO(repo.save(suc));
    }

    @Override
    public void eliminarSucursal(Long id) {
        if (!repo.existsById(id)) {
            throw new NotFoundException("Sucursal no encontrada para eliminar");
        }
        repo.deleteById(id);
    }
    
}
