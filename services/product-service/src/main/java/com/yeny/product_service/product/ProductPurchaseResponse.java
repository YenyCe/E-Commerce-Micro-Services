package com.yeny.product_service.product;
import java.math.BigDecimal;

// Record que representa la respuesta al realizar una compra de producto
//DTO que responde los datos del producto comprado.
public record ProductPurchaseResponse(
        Integer productId,
        String name,
        String description,
        BigDecimal price,
        double quantity
) {
}