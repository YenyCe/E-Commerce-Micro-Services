package com.yeny.order_service.product;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.validation.annotation.Validated;

@Validated
public record PurchaseRequest(
        @NotNull(message = "El producto es obligatorio")
        Integer productId,
        @Positive(message = "La cantidad es obligatoria")
        double quantity
) {
}