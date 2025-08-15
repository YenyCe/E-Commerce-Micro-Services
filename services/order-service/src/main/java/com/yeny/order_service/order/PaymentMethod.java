package com.yeny.order_service.order;

/**
 * Enum que define los métodos de pago soportados.
 * Se guarda en la BD como texto ("PAYPAL").
 */
public enum PaymentMethod {
    PAYPAL,
    CREDIT_CARD,
    VISA,
    MASTER_CARD

}
