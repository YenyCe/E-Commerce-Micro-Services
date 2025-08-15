package com.yeny.order_service.product;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.validation.annotation.Validated;
/**
 * DTO que representa un ítem que el cliente quiere comprar.
 * Nota: las validaciones se aplican cuando este objeto se recibe por la API o cuando lo valida el controller/service.
 */
@Validated
public record PurchaseRequest(
        @NotNull(message = "El producto es obligatorio")
        Integer productId,
        @Positive(message = "La cantidad es obligatoria")
        double quantity
) {
}