package com.yeny.customer_service.customer;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor // Constructor con todos los argumentos
@NoArgsConstructor  // Constructor vacío
@Builder            // Permite usar Customer.builder() para construir el objeto
@Getter             // Getters automáticos
@Setter             // Setters automáticos
@Document           // Indica que esta clase es un documento de MongoDB
public class Customer {

    @Id // Indica que este campo es el identificador único en MongoDB
    private String id;

    private String firstname;  // Nombre del cliente
    private String lastname;   // Apellido del cliente
    private String email;      // Correo electrónico del cliente
    private Address address;   // Dirección del cliente (usa la clase Address)
}
