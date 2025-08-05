package com.yeny.customer_service.handler;


import java.util.Map; 

/**
 * Record que representa la estructura de respuesta para errores de validación.
 * Contiene un mapa donde la clave es el nombre del campo y el valor es el mensaje de error.
 */
public record ErrorResponse(
    Map<String, String> errors // Mapa con los errores de validación por campo
) {

}
