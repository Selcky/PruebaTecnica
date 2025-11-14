package com.pruebatecnica.PruebaTecSupermercado.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.GenerationType;
import jakarta.persistence.FetchType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class DetalleVenta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // venta
    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "ventaId")
    private Venta venta;

    // producto
    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "productoId")
    private Producto producto;

    private Integer cantProd;
    private Double precioUnitario;
}
