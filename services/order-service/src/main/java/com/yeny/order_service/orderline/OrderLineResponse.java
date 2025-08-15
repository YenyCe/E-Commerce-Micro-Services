package com.yeny.order_service.orderline;

/**
 * DTO de respuesta para exponer información simplificada de una línea de pedido.
 */
public record OrderLineResponse(
        Integer id,
        double quantity
) { }
