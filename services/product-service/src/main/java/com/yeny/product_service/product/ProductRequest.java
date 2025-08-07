package com.yeny.product_service.product;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
// Record que representa la solicitud para crear o actualizar un producto
//DTO de entrada para crear productos, incluye validaciones con @NotNull, @Positive.
public record ProductRequest(

        Integer id,
        @NotNull(message = "El nombre del producto es obligatorio")
        String name,
        @NotNull(message = "Se requiere descripción del producto")
        String description,
        @Positive(message = "La cantidad disponible debe ser positiva")
        double availableQuantity,
        @Positive(message = "El precio debe ser positivo")
        BigDecimal price,
        @NotNull(message = "Se requiere categoría de producto")
        Integer categoryId
) {
}