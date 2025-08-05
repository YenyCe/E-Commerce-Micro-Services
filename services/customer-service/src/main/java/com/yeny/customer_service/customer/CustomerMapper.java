package com.yeny.customer_service.customer;

import org.springframework.stereotype.Component;

@Component // Anotación de Spring que indica que esta clase se puede inyectar como dependencia
public class CustomerMapper {

    /**
     * Convierte un objeto CustomerRequest a una entidad Customer.
     * @param request DTO recibido desde el frontend o API externa
     * @return entidad Customer lista para ser guardada
     */
    public Customer toCustomer(CustomerRequest request) {
        if (request == null) {
            return null; // Si el request es null, no hace nada
        }
        // Usa el builder de Customer para construir una entidad desde el DTO
        return Customer.builder()
                .id(request.id())               // Copia el ID del request
                .firstname(request.firstname()) // Copia el nombre
                .lastname(request.lastname())   // Copia el apellido
                .email(request.email())         // Copia el correo
                .address(request.address())     // Copia la dirección (puede ser null)
                .build();                       // Construye el objeto Customer
    }

    /**
     * Convierte una entidad Customer a un DTO CustomerResponse.
     * @param customer entidad recuperada de MongoDB
     * @return DTO con los datos del cliente para mostrar al frontend
     */
    public CustomerResponse fromCustomer(Customer customer) {
        if (customer == null) {
            return null; // Si no hay cliente, no hace nada
        }
        // Retorna un nuevo CustomerResponse con los datos del cliente
        return new CustomerResponse(
            customer.getId(),          // ID del cliente
            customer.getFirstname(),   // Nombre
            customer.getLastname(),    // Apellido
            customer.getEmail(),       // Correo
            customer.getAddress()      // Dirección
        );
    }
}
