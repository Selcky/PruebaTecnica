package com.pruebatecnica.PruebaTecSupermercado.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VentaDTO {
    private Long id;
    private LocalDate fecha;
    private String estado;

    // datos de SUCURSAL
    private Long idSucursal;

    // Lista de DetalleVentaDTO
    private List<DetalleVentaDTO> detalle;
    
    // Total de la venta
    private Double total;
}
