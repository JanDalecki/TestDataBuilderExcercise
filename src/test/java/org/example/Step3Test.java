package org.example;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class Step3Test {
    @Test
    void constructOrderWithForeignAddress() {
        Order order = new OrderBuilder()
                .withId(1L)
                .withCustomer(new CustomerBuilder()
                        .withCustomerId(1L)
                        .withName("Terry Tew")
                        .withAddress(new AddressBuilder()
                                .withStreet("1216  Clinton Street")
                                .withCity("Philadelphia")
                                .withPostalCode(19108)
                                .withCountry("United States")
                        )
                )
                .withOrderItem(new OrderItemBuilder()
                        .withName("Coffee mug")
                        .withQuantity(1)
                )
                .build();

        //Order test
        assertThat(order.getOrderId()).isEqualTo(1L);

        //OrderItem test
        assertThat(order.getOrderItems().get(0).getQuantity()).isEqualTo(1);
        assertThat(order.getOrderItems().get(0).getName()).isEqualTo("Coffee mug");

        //Customer test
        assertThat(order.getCustomer().getCustomerId()).isEqualTo(1L);
        assertThat(order.getCustomer().getName()).isEqualTo("Terry Tew");

        //Address test
        assertThat(order.getCustomer().getAddress().getCountry()).isEqualTo("UnitedStates");
        assertThat(order.getCustomer().getAddress().getPostalCode()).isEqualTo(19108);
        assertThat(order.getCustomer().getAddress().getCity()).isEqualTo("Philadelphia");
        assertThat(order.getCustomer().getAddress().getStreet()).isEqualTo("1216  Clinton Street");
    }

    @Test
    void constructOrderWithForeignAddressUsingDefaultValues() {
        Order order = new OrderBuilder()
                .withCustomer(new CustomerBuilder()
                        .withAddress(new AddressBuilder().withCountry("United States"))
                )
                .withOrderItem(new OrderItemBuilder().withName("Coffee mug").withQuantity(1))
                .build();
        //Order test
        assertThat(order.getOrderId()).isNotNull();

        //OrderItem test
        assertThat(order.getOrderItems().get(0).getQuantity()).isEqualTo(1);
        assertThat(order.getOrderItems().get(0).getName()).isEqualTo("Coffee mug");

        //Customer test
        assertThat(order.getCustomer().getCustomerId()).isNotNull();
        assertThat(order.getCustomer().getName()).isNotNull();

        //Address test
        assertThat(order.getCustomer().getAddress().getCountry()).isEqualTo("UnitedStates");
        assertThat(order.getCustomer().getAddress().getPostalCode()).isNotNull();
        assertThat(order.getCustomer().getAddress().getCity()).isNotNull();
        assertThat(order.getCustomer().getAddress().getStreet()).isNotNull();
    }
}
