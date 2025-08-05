package com.yeny.customer_service.customer;

import org.springframework.validation.annotation.Validated;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor // Genera un constructor con todos los campos
@NoArgsConstructor  // Genera un constructor vacío
@Builder            // Permite construir objetos con el patrón builder
@Getter             // Genera automáticamente los métodos get para todos los campos
@Setter             // Genera automáticamente los métodos set para todos los campos
@Validated          // Permite aplicar validaciones (si usas @Valid en los campos)
public class Address {

    private String street;        // Calle del domicilio del cliente
    private String houseNumber;   // Número de la casa
    private String zipCode;       // Código postal
}
