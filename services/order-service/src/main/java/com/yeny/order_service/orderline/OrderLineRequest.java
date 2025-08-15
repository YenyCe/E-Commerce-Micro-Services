package com.yeny.order_service.orderline;
/**
 * DTO para crear o manipular una línea de pedido desde la capa servicio.
 */
public record OrderLineRequest(
        Integer id,
        Integer orderId,
        Integer productId,
        double quantity
) {
}