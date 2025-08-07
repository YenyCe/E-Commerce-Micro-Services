package com.yeny.product_service.exception;

// Excepción personalizada que extiende de RuntimeException
public class ProductPurchaseException extends RuntimeException {
    // Constructor que recibe un mensaje y lo pasa a la superclase
    public ProductPurchaseException(String s) {
        super(s);
    }
}