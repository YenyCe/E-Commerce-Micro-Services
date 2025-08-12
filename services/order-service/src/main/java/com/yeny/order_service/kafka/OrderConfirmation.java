package com.yeny.order_service.kafka;

import java.math.BigDecimal;
import java.util.List;

import com.yeny.order_service.customer.CustomerResponse;
import com.yeny.order_service.order.PaymentMethod;
import com.yeny.order_service.product.PurchaseResponse;

public record OrderConfirmation (
        String orderReference,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        CustomerResponse customer,
        List<PurchaseResponse> products

) {
}