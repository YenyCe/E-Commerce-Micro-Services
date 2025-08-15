package com.yeny.order_service.product;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.yeny.order_service.exception.BusinessException;

import java.util.List;

import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

//Cliente REST para comunicarse con el microservicio de productos
@Service
@RequiredArgsConstructor
public class ProductClient {

    // Inyecta la URL base del servicio de productos desde application.properties o config server
    @Value("${application.config.product-url}")
    private String productUrl;

    // RestTemplate para hacer peticiones HTTP
    private final RestTemplate restTemplate;

    // Método para comprar productos, recibe lista de PurchaseRequest y devuelve lista de PurchaseResponse
    public List<PurchaseResponse> purchaseProducts(List<PurchaseRequest> requestBody) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(CONTENT_TYPE, APPLICATION_JSON_VALUE); // Define Content-Type JSON para la petición

        // Crea la entidad HTTP con el body y headers
        HttpEntity<List<PurchaseRequest>> requestEntity = new HttpEntity<>(requestBody, headers);

        // Define el tipo esperado de respuesta (lista de PurchaseResponse)
        ParameterizedTypeReference<List<PurchaseResponse>> responseType = new ParameterizedTypeReference<>() {};

        // Hace la llamada HTTP POST al endpoint /purchase del servicio de productos
        ResponseEntity<List<PurchaseResponse>> responseEntity = restTemplate.exchange(
                productUrl + "/purchase",
                POST,
                requestEntity,
                responseType
        );

        // Si la respuesta es error, lanza excepción con mensaje
        if (responseEntity.getStatusCode().isError()) {
            throw new BusinessException("Se produjo un error al procesar la compra del producto: " + responseEntity.getStatusCode());
        }

        // Devuelve el body (lista de productos comprados con detalles)
        return responseEntity.getBody();
    }
}