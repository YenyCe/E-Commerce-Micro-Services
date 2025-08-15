package com.yeny.order_service.config;


import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
//Configuración para crear el tópico Kafka
@Configuration  // Marca la clase como fuente de beans para Spring
public class KafkaOrderTopicConfig {

    // Define un bean para crear un tópico Kafka llamado "order-topic"
    @Bean
    public NewTopic orderTopic() {
        return TopicBuilder
                .name("order-topic") // Nombre del tópico
                .build();            // Construye el tópico con configuración por defecto
    }
}