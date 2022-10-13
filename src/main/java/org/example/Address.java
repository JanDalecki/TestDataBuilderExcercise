package org.example;

import lombok.Data;

@Data
public class Address {

    private final String city;
    private final String street;
    private final String country;
    private final int postalCode;


}
