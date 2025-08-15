package com.yeny.order_service.customer;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;
//Cliente Feign para comunicarse con el microservicio de clientes
@FeignClient(
    name = "customer-service",
    url = "${application.config.customer-url}"
)
public interface CustomerClient {
  // Método para obtener un cliente por su ID, retorna Optional para manejo de ausencia
  @GetMapping("/{customer-id}")
  Optional<CustomerResponse> findCustomerById(@PathVariable("customer-id") String customerId);
}