package com.yeny.product_service.handler;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.yeny.product_service.exception.ProductPurchaseException;

import java.util.HashMap;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
// Marca esta clase como un controlador de excepciones global para todos los controladores REST
@RestControllerAdvice
public class GlobalExceptionHandler {

     // Maneja las excepciones del tipo ProductPurchaseException
    @ExceptionHandler(ProductPurchaseException.class)
    public ResponseEntity<String> handle(ProductPurchaseException exp) {
        // Devuelve un mensaje simple con código 400 Bad Request
        return ResponseEntity
                .status(BAD_REQUEST)
                .body(exp.getMessage());
    }

    // Maneja errores cuando no se encuentra una entidad (por ejemplo, producto inexistente)
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> handle(EntityNotFoundException exp) {
       // Devuelve un mensaje con código 400 Bad Request
        return ResponseEntity
                .status(BAD_REQUEST)
                .body(exp.getMessage());
    }

    // Maneja errores de validación (campos inválidos en un DTO anotado con @Valid)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException exp) {
         // Crea un mapa para almacenar los errores campo -> mensaje
        var errors = new HashMap<String, String>();
         // Recorre todos los errores de validación
        exp.getBindingResult().getAllErrors()
                .forEach(error -> {
                    // Obtiene el nombre del campo con error
                    var fieldName = ((FieldError) error).getField();
                    // Obtiene el mensaje de error
                    var errorMessage = error.getDefaultMessage();
                    // Lo guarda en el mapa
                    errors.put(fieldName, errorMessage);
                });
        // Devuelve la respuesta con el mapa de errores y código 400
        return ResponseEntity
                .status(BAD_REQUEST)
                .body(new ErrorResponse(errors));
    }
}