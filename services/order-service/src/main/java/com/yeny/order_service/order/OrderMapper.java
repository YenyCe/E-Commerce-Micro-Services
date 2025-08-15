package com.yeny.order_service.order;

import org.springframework.stereotype.Service;

// Marca esta clase como un servicio manejado por Spring
@Service
public class OrderMapper {

  // Convierte un OrderRequest (DTO) a entidad Order para guardar en base de datos
  public Order toOrder(OrderRequest request) {
    if (request == null) {
      return null; // Si el request es null, devuelve null para evitar errores
    }
    // Usa builder para crear un objeto Order con campos del request
    return Order.builder()
        .id(request.id())                   // Asigna id (si viene)
        .reference(request.reference())    // Asigna referencia única
        .paymentMethod(request.paymentMethod())  // Método de pago (enum)
        .customerId(request.customerId())  // Id del cliente
        .build();
  }

  // Convierte una entidad Order a OrderResponse (DTO) para enviar en respuestas HTTP
  public OrderResponse fromOrder(Order order) {
    return new OrderResponse(
        order.getId(),                     // Id de la orden
        order.getReference(),              // Referencia
        order.getTotalAmount(),            // Monto total
        order.getPaymentMethod(),          // Método de pago
        order.getCustomerId()              // Id del cliente
    );
  }
}
