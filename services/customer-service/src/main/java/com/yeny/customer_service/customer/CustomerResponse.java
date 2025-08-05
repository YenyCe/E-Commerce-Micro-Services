package com.yeny.customer_service.customer;

// record para devolver datos al cliente
public record CustomerResponse(
    String id,
    String firstname,
    String lastname,
    String email,
    Address address
) {

}