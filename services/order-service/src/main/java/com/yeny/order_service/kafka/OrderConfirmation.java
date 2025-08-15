package com.yeny.order_service.kafka;

import java.math.BigDecimal;
import java.util.List;

import com.yeny.order_service.customer.CustomerResponse;
import com.yeny.order_service.order.PaymentMethod;
import com.yeny.order_service.product.PurchaseResponse;

// Representa el mensaje con la confirmación de pedido que se enviará a Kafka
public record OrderConfirmation (
        String orderReference,             // Referencia única del pedido
        BigDecimal totalAmount,            // Monto total del pedido
        PaymentMethod paymentMethod,       // Método de pago usado
        CustomerResponse customer,         // Información del cliente
        List<PurchaseResponse> products    // Lista de productos comprados con detalles
) {
}