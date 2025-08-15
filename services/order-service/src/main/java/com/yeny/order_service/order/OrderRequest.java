package com.yeny.order_service.order;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.yeny.order_service.product.PurchaseRequest;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.List;
/**
 * DTO (record) usado para recibir la petición de creación de pedido.
 * Validaciones con Jakarta Validation para garantizar datos correctos antes de la lógica.
 * @JsonInclude(Include.NON_EMPTY) evita serializar campos vacíos en las respuestas JSON.
 */
@JsonInclude(Include.NON_EMPTY)
public record OrderRequest(
    Integer id,
    String reference,
    @Positive(message = "El importe del pedido debe ser positivo")
    BigDecimal amount,
    @NotNull(message = "El método de pago debe ser preciso")
    PaymentMethod paymentMethod,
    @NotNull(message = "La cliente debe estar presente")
    @NotEmpty(message = "La cliente debe estar presente")
    @NotBlank(message = "El cliente debe estar presente")
    String customerId,
    // Lista de productos no puede estar vacía
    @NotEmpty(message = "Deberías comprar al menos un producto")
    List<PurchaseRequest> products
) {

}