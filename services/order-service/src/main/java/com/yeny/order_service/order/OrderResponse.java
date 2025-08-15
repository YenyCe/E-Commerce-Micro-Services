package com.yeny.order_service.order;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.math.BigDecimal;
/**
 * DTO usado para enviar información de pedidos al cliente (API response).
 * Solo incluye campos no vacíos en la serialización JSON
 */
@JsonInclude(Include.NON_EMPTY)
public record OrderResponse(
    Integer id,
    String reference,
    BigDecimal amount,
    PaymentMethod paymentMethod,
    String customerId
) {

}