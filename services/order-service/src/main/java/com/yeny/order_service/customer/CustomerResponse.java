package com.yeny.order_service.customer;
//DTO record con datos del cliente
public record CustomerResponse(
    // Representa la respuesta con información del cliente
    String id,          // Id del cliente
    String firstname,   // Nombre del cliente
    String lastname,    // Apellido del cliente
    String email        // Correo electrónico del cliente
) {

}