package com.pruebatecnica.PruebaTecSupermercado.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.pruebatecnica.PruebaTecSupermercado.model.Venta;

public interface VentaRepository extends JpaRepository<Venta, Long> {
    
}
