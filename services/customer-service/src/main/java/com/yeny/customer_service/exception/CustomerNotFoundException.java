package com.yeny.customer_service.exception;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true) // Indica que incluye equals/hashCode de la superclase RuntimeException
@Data // Genera automáticamente métodos útiles (getters, setters, etc.)
public class CustomerNotFoundException extends RuntimeException {

  private final String msg;
}