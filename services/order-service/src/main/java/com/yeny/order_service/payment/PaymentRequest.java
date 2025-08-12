package com.yeny.order_service.payment;


import java.math.BigDecimal;

import com.yeny.order_service.customer.CustomerResponse;
import com.yeny.order_service.order.PaymentMethod;

public record PaymentRequest(
    BigDecimal amount,
    PaymentMethod paymentMethod,
    Integer orderId,
    String orderReference,
    CustomerResponse customer
) {
}