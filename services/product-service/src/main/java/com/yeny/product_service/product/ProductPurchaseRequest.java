package com.yeny.product_service.product;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

// Record que representa una solicitud de compra de un producto
//DTO para pedir la compra de productos.
public record ProductPurchaseRequest(
        @NotNull(message = "El producto es obligatorio")
        Integer productId,
        @Positive(message = "La cantidad es obligatoria")
        double quantity
) {
}