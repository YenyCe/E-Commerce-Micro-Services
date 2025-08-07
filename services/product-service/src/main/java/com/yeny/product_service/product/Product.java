package com.yeny.product_service.product;



import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

import com.yeny.product_service.category.Category;


@AllArgsConstructor // Lombok genera constructor con todos los campos
@NoArgsConstructor // Lombok genera constructor vacío (sin parámetros)
@Builder // Permite construir objetos con patrón builder
@Getter // Genera getters automáticamente
@Setter // Genera setters automáticamente
@Entity // Indica que esta clase es una entidad JPA
public class Product {
   
    @Id // Llave primaria autogenerada
    @GeneratedValue
    private Integer id;

    private String name; // Nombre del producto
    private String description; // Descripción del producto
    private double availableQuantity; // Cantidad disponible en inventario
    private BigDecimal price; // Precio del producto (uso de BigDecimal para valores monetarios)

   
    @ManyToOne // Relación muchos productos a una categoría
    @JoinColumn(name = "category_id") // Llave foránea hacia la tabla de categorías
    private Category category;
}
