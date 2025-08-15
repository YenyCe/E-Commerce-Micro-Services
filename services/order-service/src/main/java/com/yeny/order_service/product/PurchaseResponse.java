package com.yeny.order_service.product;

import java.math.BigDecimal;
/**
 * DTO que representa la información de un producto devuelta por el product-service después de la compra.
 */
public record PurchaseResponse(
        Integer productId,     // Id del producto
        String name,           // Nombre del producto
        String description,    // Descripción del producto
        BigDecimal price,      // Precio unitario
        double quantity        // Cantidad comprada
) {
}