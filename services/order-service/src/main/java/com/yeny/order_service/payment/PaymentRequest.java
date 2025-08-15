package com.yeny.order_service.payment;


import java.math.BigDecimal;

import com.yeny.order_service.customer.CustomerResponse;
import com.yeny.order_service.order.PaymentMethod;

// Representa la solicitud de pago que se envía al microservicio de pagos
public record PaymentRequest(
    BigDecimal amount,             // Monto total a pagar
    PaymentMethod paymentMethod,   // Método de pago elegido
    Integer orderId,               // Id del pedido
    String orderReference,         // Referencia del pedido
    CustomerResponse customer      // Información del cliente que realiza el pago

) {
}