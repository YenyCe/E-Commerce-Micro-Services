package com.yeny.order_service.orderline;

import com.yeny.order_service.order.Order;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entidad que representa una línea dentro de un pedido (un producto comprado y su cantidad).
 */
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "customer_line")
public class OrderLine {

    @Id
    @GeneratedValue
    private Integer id;
    @ManyToOne             // Relación muchos a uno con Order (muchas líneas a una orden)
    @JoinColumn(name = "order_id") // Clave foránea en la tabla para la orden
    private Order order;
    // Identificador del producto comprado (referencia al catálogo / product service).
    private Integer productId;
    private double quantity;
}
