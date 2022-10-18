package org.example;

public class OrderItemBuilder {
    String name = "ABC";
    int quantity = 0;

    private OrderItem build() {
        return new OrderItem(name, quantity);
    }
}
