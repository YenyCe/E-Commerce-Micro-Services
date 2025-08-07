package com.yeny.product_service.product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// Repositorio JPA para realizar operaciones en la tabla de productos
public interface ProductRepository extends JpaRepository<Product, Integer> {

     // Método personalizado para buscar productos por una lista de IDs y ordenarlos por ID
    List<Product> findAllByIdInOrderById(List<Integer> ids);
}