package com.pruebatecnica.PruebaTecSupermercado.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.pruebatecnica.PruebaTecSupermercado.model.Producto;
import java.util.Optional;

public interface ProductoRepository extends JpaRepository<Producto, Long>{
    // Buscar productos por nombre
    Optional<Producto> findByNombre(String nombre);
}
