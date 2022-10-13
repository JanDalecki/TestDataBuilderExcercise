package org.example;


import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class Step1Test {

    @Test
    void testingAddress() {
        Address address = new AddressBuilder().build();
        assertThat(address.getCity()).isNotNull();
        assertThat(address.getPostalCode()).isNotZero();
        assertThat(address.getStreet()).isNotNull();
        assertThat(address.getCountry()).isNotNull();
    }

    @Test
    void testingCustomer() {
        Customer customer = new CustomerBuilder().build();
        assertThat(customer.getCustomerId()).isNotNull();
        assertThat(customer.getName()).isNotNull();
        assertThat(customer.getAddress()).isNotNull();
    }
    @Test
    void testingOrder() {
        Order order = new OrderBuilder().build();
        assertThat(order.getOrderId()).isNotNull();
        assertThat(order.getCouponCode()).isNotNull();
        assertThat(order.getOrderItems()).isNotNull();
        assertThat(order.getCustomer()).isNotNull();
        assertThat(order.getDiscountRate()).isNotNull();
    }
    @Test
    void testingOrderItem() {
        OrderItem orderItem = new OrderItem().build();
        assertThat(orderItem.getName()).isNotNull();
        assertThat(orderItem.getQuantity()).isNotNull();
    }
}
