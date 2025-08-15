package com.yeny.order_service.order;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yeny.order_service.customer.CustomerClient;
import com.yeny.order_service.exception.BusinessException;
import com.yeny.order_service.kafka.OrderConfirmation;
import com.yeny.order_service.kafka.OrderProducer;
import com.yeny.order_service.orderline.OrderLineRequest;
import com.yeny.order_service.orderline.OrderLineService;
import com.yeny.order_service.payment.PaymentClient;
import com.yeny.order_service.payment.PaymentRequest;
import com.yeny.order_service.product.ProductClient;
import com.yeny.order_service.product.PurchaseRequest;

import java.util.List;
import java.util.stream.Collectors;

// Marca esta clase como un servicio de Spring, es un componente para la lógica de negocio
@Service
@RequiredArgsConstructor
public class OrderService {

    // Repositorio JPA para acceder a la tabla de órdenes en la base de datos
    private final OrderRepository repository;

    // Mapper para convertir entre entidad Order y DTOs (Request y Response)
    private final OrderMapper mapper;

    // Cliente Feign para consultar información de clientes desde el microservicio customer-service
    private final CustomerClient customerClient;

    // Cliente Feign para solicitar pagos al microservicio payment-service
    private final PaymentClient paymentClient;

    // Cliente REST para interactuar con el microservicio product-service y reservar productos
    private final ProductClient productClient;

    // Servicio para manejar las líneas de pedido (productos dentro de una orden)
    private final OrderLineService orderLineService;

    // Productor Kafka para enviar mensajes de confirmación de orden
    private final OrderProducer orderProducer;

    // Método que crea una orden completa, la transacción abarca todo el método para mantener consistencia
    @Transactional
    public Integer createOrder(OrderRequest request) {
        // Busca al cliente por ID usando cliente Feign, lanza excepción si no existe
        var customer = this.customerClient.findCustomerById(request.customerId())
                .orElseThrow(() -> new BusinessException("No se puede crear un pedido:: No existe ningún cliente con el ID proporcionado"));

        // Llama al microservicio product para reservar los productos pedidos, devuelve detalles de los productos comprados
        var purchasedProducts = productClient.purchaseProducts(request.products());

        // Convierte el OrderRequest a entidad Order y la guarda en la base de datos
        var order = this.repository.save(mapper.toOrder(request));

        // Por cada producto en la orden, crea y guarda una línea de pedido (OrderLine)
        for (PurchaseRequest purchaseRequest : request.products()) {
            orderLineService.saveOrderLine(
                    new OrderLineRequest(
                            null,
                            order.getId(),
                            purchaseRequest.productId(),
                            purchaseRequest.quantity()
                    )
            );
        }

        // Construye el objeto PaymentRequest con los datos necesarios para procesar el pago
        var paymentRequest = new PaymentRequest(
                request.amount(),
                request.paymentMethod(),
                order.getId(),
                order.getReference(),
                customer
        );

        // Llama al microservicio de pagos para procesar el pago de la orden
        paymentClient.requestOrderPayment(paymentRequest);

        // Envía un mensaje Kafka con la confirmación de la orden para notificaciones o procesamiento posterior
        orderProducer.sendOrderConfirmation(
                new OrderConfirmation(
                        request.reference(),
                        request.amount(),
                        request.paymentMethod(),
                        customer,
                        purchasedProducts
                )
        );

        // Devuelve el ID de la orden creada
        return order.getId();
    }

    // Obtiene todas las órdenes y las convierte a respuestas DTO para la API
    public List<OrderResponse> findAllOrders() {
        return this.repository.findAll()
                .stream()
                .map(this.mapper::fromOrder)
                .collect(Collectors.toList());
    }

    // Busca una orden por ID, si no existe lanza excepción
    public OrderResponse findById(Integer id) {
        return this.repository.findById(id)
                .map(this.mapper::fromOrder)
                .orElseThrow(() -> new EntityNotFoundException(String.format("No order found with the provided ID: %d", id)));
    }
}
