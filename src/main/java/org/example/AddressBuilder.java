package org.example;

public class AddressBuilder {

    private String city = "Wroclaw";
    private String street = "Sample";
    private String country = "Poland";
    private int postalCode = 123;

    public Address build() {
        return new Address(city, street, country, postalCode);
    }
}
