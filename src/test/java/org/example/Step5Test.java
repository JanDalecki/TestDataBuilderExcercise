package org.example;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.example.OrderBuilder.anOrder;

public class Step5Test {

    @Test
    void butTest() {
        OrderBuilder coffeeMugAndTeaCup = anOrder()
                .with(anOrderItem().withName("Coffee mug").withQuantity(1))
                .with(anOrderItem().withName("Tea cup").withQuantity(1));

        Order orderWithDiscount = coffeeMugAndTeaCup.but().withDiscountRate(0.1).build();
        Order orderWithCouponCode = coffeeMugAndTeaCup.but().withCouponCode("HALFOFF").build();

        assertThat(orderWithDiscount.getDiscountRate()).isNotEqualTo(orderWithCouponCode.getDiscountRate());
        assertThat(orderWithDiscount.getCouponCode()).isNotEqualTo(orderWithCouponCode.getCouponCode());

    }
}
