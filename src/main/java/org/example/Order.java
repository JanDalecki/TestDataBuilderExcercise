package org.example;

import lombok.Data;

import java.util.List;

@Data
public class Order {
    private final Long orderId;
    private final Customer customer;
    private final List<OrderItem> orderItems;
    private final Double discountRate;
    private final String couponCode;
}
