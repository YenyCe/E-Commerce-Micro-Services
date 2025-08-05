package com.yeny.customer_service.customer;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController // Marca esta clase como un controlador REST (devuelve JSON)
@RequestMapping("/api/va/customer") // Prefijo para todas las rutas de esta clase
@RequiredArgsConstructor // Genera constructor para inyectar dependencias final
public class CustomerController {

    private final CustomerService service; // Inyección del servicio de lógica de negocio

    /**
     * Crea un nuevo cliente.
     * @param request datos del cliente recibidos desde el frontend
     * @return ID del cliente creado
     */
    @PostMapping // POST /api/va/customer
    public ResponseEntity<String> createCustomer(@RequestBody @Valid CustomerRequest request) {
        // Valida el request y llama al servicio para guardar el cliente
        return ResponseEntity.ok(service.createCustomer(request));
    }

    /**
     * Actualiza un cliente existente.
     * @param request datos actualizados del cliente
     * @return 202 Accepted si se actualizó exitosamente
     */
    @PutMapping // PUT /api/va/customer
    public ResponseEntity<Void> updateCustomer(@RequestBody @Valid CustomerRequest request) {
        // Valida el request y actualiza el cliente
        this.service.updateCustomer(request);
        return ResponseEntity.accepted().build(); // Devuelve HTTP 202 sin cuerpo
    }

    /**
     * Obtiene todos los clientes.
     * @return lista de clientes
     */
    @GetMapping // GET /api/va/customer
    public ResponseEntity<List<CustomerResponse>> findAll() {
        // Devuelve la lista de todos los clientes
        return ResponseEntity.ok(this.service.findAllCustomers());
    }

    /**
     * Verifica si existe un cliente por ID.
     * @param customerId ID a buscar
     * @return true si existe, false si no
     */
    @GetMapping("/exists/{customer-id}") // GET /api/va/customer/exists/{id}
    public ResponseEntity<Boolean> existsById(@PathVariable("customer-id") String customerId) {
        // Verifica existencia por ID
        return ResponseEntity.ok(this.service.existsById(customerId));
    }

    /**
     * Busca un cliente por su ID.
     * @param customerId ID del cliente
     * @return cliente encontrado
     */
    @GetMapping("/{customer-id}") // GET /api/va/customer/{id}
    public ResponseEntity<CustomerResponse> findById(@PathVariable("customer-id") String customerId) {
        // Busca y devuelve un cliente por ID
        return ResponseEntity.ok(this.service.findById(customerId));
    }

    /**
     * Elimina un cliente por ID.
     * @param customerId ID del cliente a eliminar
     * @return HTTP 202 si se eliminó correctamente
     */
    @DeleteMapping("/{customer-id}") // DELETE /api/va/customer/{id}
    public ResponseEntity<Void> delete(@PathVariable("customer-id") String customerId) {
        // Elimina el cliente
        this.service.deleteCustomer(customerId);
        return ResponseEntity.accepted().build(); // Devuelve 202 Accepted
    }
}
