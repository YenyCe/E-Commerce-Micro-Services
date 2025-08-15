package com.yeny.order_service.orderline;

import org.springframework.stereotype.Service;

import com.yeny.order_service.order.Order;

// Servicio para convertir entre entidad OrderLine y DTOs
@Service
public class OrderLineMapper {

    // Convierte un OrderLineRequest a entidad OrderLine
    public OrderLine toOrderLine(OrderLineRequest request) {
        return OrderLine.builder()
                .id(request.orderId())   
                .productId(request.productId())   // Asigna id producto
                .order(
                        Order.builder()
                                .id(request.orderId())   // Solo asigna el id de la orden para la relación
                                .build()
                )
                .quantity(request.quantity())    // Asigna cantidad
                .build();
    }

    // Convierte entidad OrderLine a OrderLineResponse (para respuesta)
    public OrderLineResponse toOrderLineResponse(OrderLine orderLine) {
        return new OrderLineResponse(
                orderLine.getId(),         // Id de la línea
                orderLine.getQuantity()    // Cantidad
        );
    }
}
