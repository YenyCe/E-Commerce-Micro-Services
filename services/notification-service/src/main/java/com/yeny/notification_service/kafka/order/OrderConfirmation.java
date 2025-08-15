package com.yeny.notification_service.kafka.order;

import java.math.BigDecimal;
import java.util.List;

import com.yeny.notification_service.kafka.payment.PaymentMethod;

public record OrderConfirmation(
    String orderReference,
    BigDecimal totalAmount,
    PaymentMethod paymentMethod,
    Customer customer,
    List<Product> products
) {


    
} 
