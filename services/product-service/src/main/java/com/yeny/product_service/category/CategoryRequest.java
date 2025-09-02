package com.yeny.product_service.category;

import jakarta.validation.constraints.NotBlank;
//xpara crear/editar
public record CategoryRequest(
        @NotBlank String name,
        String description
) {}