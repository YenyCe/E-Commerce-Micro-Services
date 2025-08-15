package com.yeny.order_service.payment;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
//Cliente Feign para comunicación con el microservicio de pagos

// Define un cliente Feign para llamar al servicio de pagos remoto
@FeignClient(
    name = "product-service",
    url = "${application.config.payment-url}"
)
public interface PaymentClient {
  // Método para solicitar el pago de un pedido, mapeado a POST sin path adicional (en la raíz)
  @PostMapping
  Integer requestOrderPayment(@RequestBody PaymentRequest request);
}