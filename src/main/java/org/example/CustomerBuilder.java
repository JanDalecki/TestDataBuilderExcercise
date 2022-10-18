package org.example;

public class CustomerBuilder {

    private Long customerId = 1L;
    private String name = "Test";
    private Address address = new AddressBuilder().build();


    public Customer build() {
        return new Customer(customerId, name, address);
    }
}
