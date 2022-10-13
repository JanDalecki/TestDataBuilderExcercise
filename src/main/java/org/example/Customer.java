package org.example;


import lombok.Data;

@Data
public class Customer {
    private final Long customerId;
    private final String name;
    private final Address address;
}
