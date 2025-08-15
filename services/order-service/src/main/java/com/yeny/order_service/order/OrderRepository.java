package com.yeny.order_service.order;

import org.springframework.data.jpa.repository.JpaRepository;


// Repositorio JPA para la entidad Order, hereda de JpaRepository que provee CRUD y consultas básicas
// JpaRepository<Order, Integer>: maneja entidades Order con clave primaria tipo Integer
public interface OrderRepository extends JpaRepository<Order, Integer> {

}