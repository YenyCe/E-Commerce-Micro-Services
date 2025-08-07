package com.yeny.product_service.product;

import org.springframework.stereotype.Service;
import com.yeny.product_service.category.Category;
// Clase encargada de convertir entre entidades y DTOs
//Clase para mapear entre entidades (Product) y DTOs (ProductRequest, ProductResponse...).
@Service
public class ProductMapper {

     // Convierte un DTO de solicitud a una entidad Product
    public Product toProduct(ProductRequest request) {
        return Product.builder()
                .id(request.id())
                .name(request.name())
                .description(request.description())
                .availableQuantity(request.availableQuantity())
                .price(request.price())
                .category(
                        Category.builder()
                                .id(request.categoryId())
                                .build()
                )
                .build();
    }

    // Convierte una entidad Product a un DTO de respuesta
    public ProductResponse toProductResponse(Product product) {
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getAvailableQuantity(),
                product.getPrice(),
                product.getCategory().getId(),
                product.getCategory().getName(),
                product.getCategory().getDescription()
        );
    }

     // Convierte a un DTO especial para mostrar la compra de un producto
    public ProductPurchaseResponse toproductPurchaseResponse(Product product, double quantity) {
        return new ProductPurchaseResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                quantity
        );
    }
}