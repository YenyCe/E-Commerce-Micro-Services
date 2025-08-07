package com.yeny.product_service.handler;

import java.util.Map;

// Record que representa la estructura de la respuesta de error
public record ErrorResponse(
     // Mapa con los errores: nombreCampo -> mensajeError
    Map<String, String> errors
) {

}