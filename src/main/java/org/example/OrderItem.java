package org.example;

import lombok.Data;

@Data
public class OrderItem {
    private final String name;
    private final int quantity;
}
