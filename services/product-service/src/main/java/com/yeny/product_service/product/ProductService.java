package com.yeny.product_service.product;


import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yeny.product_service.exception.ProductPurchaseException;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service // Indica que esta clase es un servicio gestionado por Spring
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository repository; // Repositorio para operaciones CRUD en productos
    private final ProductMapper mapper;  // Clase para mapear entre entidades y DTOs

    // Método para crear un producto a partir de un DTO de entrada
    public Integer createProduct(ProductRequest request) {
        var product = mapper.toProduct(request); // Convierte el DTO en una entidad Product
        return repository.save(product).getId(); // Guarda en la base de datos y devuelve el ID generado
    }

    // Método para buscar un producto por ID
    public ProductResponse findById(Integer id) {
        return repository.findById(id)
                .map(mapper::toProductResponse) // Si lo encuentra, lo convierte en ProductResponse
                .orElseThrow(() -> new EntityNotFoundException("Product not found with ID:: " + id));// Si no lo encuentra, lanza una excepción
    }
    // Devuelve todos los productos registrados
    public List<ProductResponse> findAll() {
        return repository.findAll() // obtiene todos los productos
                .stream() // los convierte en stream
                .map(mapper::toProductResponse) // transforma cada producto a su DTO
                .collect(Collectors.toList()); // recolecta en una lista
    }

    // Método para realizar la compra de productos
    @Transactional(rollbackFor = ProductPurchaseException.class)
    public List<ProductPurchaseResponse> purchaseProducts(List<ProductPurchaseRequest> request) {

        // Extrae los IDs de los productos solicitados
        var productIds = request
                .stream()
                .map(ProductPurchaseRequest::productId)
                .toList();

        // Obtiene los productos existentes en la base de datos, ordenados por ID
        var storedProducts = repository.findAllByIdInOrderById(productIds);

        // Valida que todos los productos solicitados existan
        if (productIds.size() != storedProducts.size()) {
            throw new ProductPurchaseException("Uno o más productos no existen");
        }

        // Ordena las solicitudes por ID para que coincidan con los productos recuperados
        var sortedRequest = request
                .stream()
                .sorted(Comparator.comparing(ProductPurchaseRequest::productId))
                .toList();

        // Lista que almacenará las respuestas de los productos comprados
        var purchasedProducts = new ArrayList<ProductPurchaseResponse>();

        // Recorre los productos y sus solicitudes correspondientes
        for (int i = 0; i < storedProducts.size(); i++) {
            var product = storedProducts.get(i);
            var productRequest = sortedRequest.get(i);

            // Verifica si hay suficiente stock
            if (product.getAvailableQuantity() < productRequest.quantity()) {
                throw new ProductPurchaseException("Insufficient stock quantity for product with ID:: " + productRequest.productId());
            }

            // Resta la cantidad comprada al stock disponible
            var newAvailableQuantity = product.getAvailableQuantity() - productRequest.quantity();
            product.setAvailableQuantity(newAvailableQuantity);

            // Guarda el producto actualizado
            repository.save(product);

            // Agrega el producto comprado a la respuesta
            purchasedProducts.add(mapper.toproductPurchaseResponse(product, productRequest.quantity()));
        }

        // Devuelve la lista de productos comprados
        return purchasedProducts;
    }

}