package com.yeny.payment_service.notification;

import java.math.BigDecimal;

import com.yeny.payment_service.payment.PaymentMethod;

public record PaymentNotificationRequest(
        String orderReference,
        BigDecimal amount,
        PaymentMethod paymentMethod,
        String customerFirstname,
        String customerLastname,
        String customerEmail
) {
}