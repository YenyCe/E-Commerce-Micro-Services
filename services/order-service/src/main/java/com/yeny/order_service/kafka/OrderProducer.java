package com.yeny.order_service.kafka;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import static org.springframework.kafka.support.KafkaHeaders.TOPIC;
//Servicio que envía mensajes al tópico Kafka
@Service
@RequiredArgsConstructor
@Slf4j
public class OrderProducer {
    // KafkaTemplate para enviar mensajes con clave String y valor OrderConfirmation
    private final KafkaTemplate<String, OrderConfirmation> kafkaTemplate;

    // Método que envía la confirmación de pedido al tópico Kafka llamado "order-topic"
    public void sendOrderConfirmation(OrderConfirmation orderConfirmation) {
        log.info("Envío de confirmación del pedido");

        // Construye el mensaje con payload y header para el tópico
        Message<OrderConfirmation> message = MessageBuilder
                .withPayload(orderConfirmation)
                .setHeader(TOPIC, "order-topic")
                .build();

        // Envía el mensaje al tópico configurado
        kafkaTemplate.send(message);
    }
}