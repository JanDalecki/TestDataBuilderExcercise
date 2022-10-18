package org.example;


import lombok.Data;

import java.util.List;

@Data
public class Customer {
    private final Long customerId;
    private final String name;
    private final Address address;

}
