package org.example;

import java.util.ArrayList;
import java.util.List;

public class OrderBuilder {

    private Long orderId = 1L;
    private Customer customer = new CustomerBuilder().build();

    private List<OrderItem> orderItems = new ArrayList<>();

    private Double discountRate = 1.2;
    private String couponCode = "ABC";

    private Order build() {
        return new Order(orderId, customer, orderItems, discountRate, couponCode);
    }
}
