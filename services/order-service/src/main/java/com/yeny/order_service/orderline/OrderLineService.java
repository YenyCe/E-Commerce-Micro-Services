package com.yeny.order_service.orderline;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderLineService {

    private final OrderLineRepository repository;   // Repositorio para persistir líneas de pedido
    private final OrderLineMapper mapper;            // Mapper para convertir entre entidad y DTOs

    // Guarda una línea de pedido y devuelve su ID
    public Integer saveOrderLine(OrderLineRequest request) {
        var order = mapper.toOrderLine(request);   // Convierte DTO a entidad
        return repository.save(order).getId();     // Guarda y devuelve id generado
    }

    // Busca todas las líneas por id de orden y convierte a respuestas DTO
    public List<OrderLineResponse> findAllByOrderId(Integer orderId) {
        return repository.findAllByOrderId(orderId)
                .stream()
                .map(mapper::toOrderLineResponse)
                .collect(Collectors.toList());
    }
}