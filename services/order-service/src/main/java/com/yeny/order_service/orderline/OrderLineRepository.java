package com.yeny.order_service.orderline;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// Extiende JpaRepository para operaciones CRUD en OrderLine
public interface OrderLineRepository extends JpaRepository<OrderLine, Integer> {

     // Consulta para obtener todas las líneas de pedido por id de orden
    List<OrderLine> findAllByOrderId(Integer orderId);
}