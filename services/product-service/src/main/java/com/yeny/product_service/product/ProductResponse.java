package com.yeny.product_service.product;

import java.math.BigDecimal;
//DTO de salida que devuelve información completa del producto y su categoría.
public record ProductResponse(
        Integer id,
        String name,
        String description,
        double availableQuantity,
        BigDecimal price,
        Integer categoryId,
        String categoryName,
        String categoryDescription
) {
}
