package com.yeny.product_service.product;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService service;

    // Endpoint POST para crear un nuevo producto
    @PostMapping
    public ResponseEntity<Integer> createProduct(
            @RequestBody @Valid ProductRequest request
    ) {
        // Retorna el ID del producto creado con un status 200 OK
        return ResponseEntity.ok(service.createProduct(request));
    }

    // Endpoint POST para realizar una compra de productos
    @PostMapping("/compra")
    public ResponseEntity<List<ProductPurchaseResponse>> purchaseProducts(
            @RequestBody List<ProductPurchaseRequest> request
    ) {
        // Retorna una lista con los detalles de los productos comprados
        return ResponseEntity.ok(service.purchaseProducts(request));
    }

     // Endpoint GET para obtener un producto por su ID
    @GetMapping("/{product-id}")
    public ResponseEntity<ProductResponse> findById(
            @PathVariable("product-id") Integer productId
    ) {
        // Devuelve los detalles del producto si existe
        return ResponseEntity.ok(service.findById(productId));
    }

     // Endpoint GET para obtener todos los productos registrados
    @GetMapping
    public ResponseEntity<List<ProductResponse>> findAll() {
        // Retorna una lista de todos los productos
        return ResponseEntity.ok(service.findAll());
    }
}