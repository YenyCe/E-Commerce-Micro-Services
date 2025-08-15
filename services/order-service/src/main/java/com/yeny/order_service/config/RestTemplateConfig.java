package com.yeny.order_service.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
//Configuración para crear un bean RestTemplate reutilizable
@Configuration  // Marca la clase como configuración para Spring
public class RestTemplateConfig {
    // Define un bean RestTemplate para hacer llamadas HTTP REST
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();  // Instancia y devuelve un RestTemplate estándar
    }
}