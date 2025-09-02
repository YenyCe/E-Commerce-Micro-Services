package com.yeny.customer_service.customer;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import com.yeny.customer_service.exception.CustomerNotFoundException;
import lombok.RequiredArgsConstructor;

@Service // Indica que esta clase es un servicio de Spring (componente de lógica de negocio)
@RequiredArgsConstructor // Genera un constructor con todos los campos marcados como final
public class CustomerService {

    // Inyección del repositorio para acceder a la base de datos MongoDB
    private final CustomerRepository repository;

    // Inyección del mapper para transformar entre entidades y DTOs
    private final CustomerMapper mapper;

    //Crea un nuevo cliente a partir de la solicitud recibida. param request datos del cliente a crear
    public CustomerResponse createCustomer(CustomerRequest request) {
        var customer = this.repository.save(mapper.toCustomer(request));
        return mapper.fromCustomer(customer); // Devuelve un objeto DTO
    }

    public CustomerResponse updateCustomer(String id, CustomerRequest request) {
        // Buscar cliente existente
        var customer = repository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Cliente con id %s no encontrado".formatted(id)));

        // Reemplazar TODOS los campos
        customer.setFirstname(request.firstname());
        customer.setLastname(request.lastname());
        customer.setEmail(request.email());
        customer.setAddress(request.address());

        // Guardar y devolver un custommer mapper
        var saved = repository.save(customer);
        return mapper.fromCustomer(saved);
    }

    // Devuelve todos los clientes registrados.
    public List<CustomerResponse> findAllCustomers() {
        return this.repository.findAll() // Obtiene todos los documentos de MongoDB
                .stream() // Convierte la lista a stream
                .map(this.mapper::fromCustomer) // Transforma cada entidad a DTO de respuesta
                .collect(Collectors.toList()); // Vuelve a lista
    }

    // Busca un cliente por su ID.
    public CustomerResponse findById(String id) {
        return this.repository.findById(id)
                .map(mapper::fromCustomer) // Si existe, transforma a DTO  con los datos del cliente
                .orElseThrow(() -> new CustomerNotFoundException(
                        String.format("No se encontró ningún cliente con el id proporcionado: %s", id)));
    }

    //Verifica si un cliente existe por su ID.
    public boolean existsById(String id) {
        return this.repository.findById(id).isPresent(); // Devuelve si encontró el cliente true si existe, false si no
    }

    // Elimina un cliente por su ID.
    public void deleteCustomer(String id) {
        this.repository.deleteById(id); // Borra el documento de MongoDB
    }
}
