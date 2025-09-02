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
@RequestMapping("/api/v1/customers") // Prefijo para todas las rutas de esta clase
@RequiredArgsConstructor // Genera constructor para inyectar dependencias final
public class CustomerController {

    private final CustomerService service; // Inyección del servicio de lógica de negocio

    //Crea un nuevo cliente. request datos del cliente recibidos desde el frontend
    @PostMapping
    public ResponseEntity<CustomerResponse> createCustomer(@RequestBody @Valid CustomerRequest request) {
        return ResponseEntity.ok(service.createCustomer(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerResponse> updateCustomer(@PathVariable String id,@RequestBody @Valid CustomerRequest request) {
        var updatedCustomer = service.updateCustomer(id, request);
        return ResponseEntity.ok(updatedCustomer);
    }

    //Obtiene todos los clientes.
    @GetMapping 
    public ResponseEntity<List<CustomerResponse>> findAll() {
        // Devuelve la lista de todos los clientes
        return ResponseEntity.ok(this.service.findAllCustomers());
    }
    
     // Verifica si existe un cliente por ID. customerId ID a buscar
    @GetMapping("/exists/{customer-id}") // GET /api/va/customer/exists/{id}
    public ResponseEntity<Boolean> existsById(@PathVariable("customer-id") String customerId) {
        // Verifica existencia por ID
        return ResponseEntity.ok(this.service.existsById(customerId));
    }

    // Busca un cliente por su ID.
    @GetMapping("/{customer-id}") // GET /api/va/customer/{id}
    public ResponseEntity<CustomerResponse> findById(@PathVariable("customer-id") String customerId) {
        // Busca y devuelve un cliente por ID
        return ResponseEntity.ok(this.service.findById(customerId));
    }

    @DeleteMapping("/{customer-id}") // DELETE /api/va/customer/{id}
    public ResponseEntity<Void> delete(@PathVariable("customer-id") String customerId) {
        // Elimina el cliente
        this.service.deleteCustomer(customerId);
        return ResponseEntity.accepted().build(); // Devuelve 202 Accepted
    }
}
