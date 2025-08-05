package com.yeny.customer_service.handler;

import java.util.HashMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.yeny.customer_service.exception.CustomerNotFoundException;

@RestControllerAdvice
// Indica que esta clase maneja excepciones de forma global para todos los controladores REST
public class GlobalExceptionHandler {

    /**
     * Maneja las excepciones de tipo CustomerNotFoundException.
     * @param exp la excepción lanzada cuando no se encuentra un cliente
     * @return ResponseEntity con mensaje y estado HTTP 404 (Not Found)
     */
    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<String> handle(CustomerNotFoundException exp) {
        // Devuelve el mensaje de la excepción con código 404
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(exp.getMsg());
    }

    /**
     * Maneja excepciones de validación cuando los argumentos no cumplen con las reglas.
     * @param exp excepción lanzada cuando falla la validación de argumentos (por ejemplo, @NotNull)
     * @return ResponseEntity con un mapa de errores y estado HTTP 400 (Bad Request)
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException exp) {
        // Mapa para guardar el nombre del campo y su mensaje de error
        var errors = new HashMap<String, String>();

        // Recorre todos los errores de validación y los agrega al mapa
        exp.getBindingResult().getAllErrors()
                .forEach(error -> {
                    // Obtiene el nombre del campo que falló la validación
                    var fieldName = ((FieldError) error).getField();
                    // Obtiene el mensaje de error asociado
                    var errorMessage = error.getDefaultMessage();
                    errors.put(fieldName, errorMessage);
                });

        // Devuelve el mapa de errores con código 400 (Bad Request)
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse(errors));
    }
}
