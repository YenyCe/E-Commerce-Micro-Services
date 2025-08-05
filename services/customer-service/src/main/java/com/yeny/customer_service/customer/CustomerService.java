package com.yeny.customer_service.customer;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.yeny.customer_service.exception.CustomerNotFoundException;

import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;

@Service // Indica que esta clase es un servicio de Spring (componente de lógica de negocio)
@RequiredArgsConstructor // Genera un constructor con todos los campos marcados como final
public class CustomerService {

    // Inyección del repositorio para acceder a la base de datos MongoDB
    private final CustomerRepository repository;

    // Inyección del mapper para transformar entre entidades y DTOs
    private final CustomerMapper mapper;

    /**
     * Crea un nuevo cliente a partir de la solicitud recibida.
     * @param request datos del cliente a crear
     * @return ID del cliente creado
     */
    public String createCustomer(CustomerRequest request) {
        // Transforma el request a entidad y guarda en MongoDB
        var customer = this.repository.save(mapper.toCustomer(request));
        return customer.getId(); // Devuelve el ID generado
    }

    /**
     * Actualiza los datos de un cliente existente.
     * @param request solicitud con datos actualizados
     */
    public void updateCustomer(CustomerRequest request) {
        // Busca el cliente por ID o lanza excepción si no existe
        var customer = this.repository.findById(request.id())
                .orElseThrow(() -> new CustomerNotFoundException(
                        String.format(
                                "No se puede actualizar el cliente: No se encontró ningún cliente con el ID proporcionado:%s",
                                request.id())));
        // Actualiza los campos necesarios
        mergeCustomer(customer, request);
        // Guarda los cambios
        this.repository.save(customer);
    }

    /**
     * Actualiza campos específicos del cliente si vienen en la solicitud.
     * @param customer entidad existente
     * @param request solicitud con nuevos datos
     */
    private void mergeCustomer(Customer customer, CustomerRequest request) {
        // Verifica si hay un nuevo nombre
        if (StringUtils.isNotBlank(request.firstname())) {
            customer.setFirstname(request.firstname());
        }

        // Verifica si hay un nuevo email
        if (StringUtils.isNotBlank(request.email())) {
            customer.setEmail(request.email());
        }

        // Verifica si hay nueva dirección
        if (request.address() != null) {
            customer.setAddress(request.address());
        }
    }

    /**
     * Devuelve todos los clientes registrados.
     * @return lista de clientes
     */
    public List<CustomerResponse> findAllCustomers() {
        return this.repository.findAll() // Obtiene todos los documentos de MongoDB
                .stream() // Convierte la lista a stream
                .map(this.mapper::fromCustomer) // Transforma cada entidad a DTO de respuesta
                .collect(Collectors.toList()); // Vuelve a lista
    }

    /**
     * Busca un cliente por su ID.
     * @param id identificador del cliente
     * @return DTO con los datos del cliente
     */
    public CustomerResponse findById(String id) {
        return this.repository.findById(id)
                .map(mapper::fromCustomer) // Si existe, transforma a DTO
                .orElseThrow(() -> new CustomerNotFoundException(
                        String.format("No se encontró ningún cliente con el id proporcionado: %s", id)));
    }

    /**
     * Verifica si un cliente existe por su ID.
     * @param id identificador del cliente
     * @return true si existe, false si no
     */
    public boolean existsById(String id) {
        return this.repository.findById(id).isPresent(); // Devuelve si encontró el cliente
    }

    /**
     * Elimina un cliente por su ID.
     * @param id identificador del cliente
     */
    public void deleteCustomer(String id) {
        this.repository.deleteById(id); // Borra el documento de MongoDB
    }
}
