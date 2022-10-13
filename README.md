# Exercise test data builders

Creating data for testing with a lot of parameters might be cumbersome and later unreadable:

```java
@Test
void constructOrderWithForeignAddress() {
    Address address = new Address("1216  Clinton Street", "Philadelphia", "19108", "United States");
    Customer customer = new Customer(1L, "Terry Tew", address);
    Order order = new Order(1L, customer, 0.0, null);
    order.addOrderItem(new OrderItem("Coffee mug", 1));
    // ...
}
```

This is why there are more intuitive ways to create new data for testing using the builder pattern.

## Step 1.

For every Builder implement some default values and builder methods.
Example build method for address:
```java
public Address build() {
    return new Address(city, street, country, postalCode);
}
```

This way we can create a new Address with default values.

## Step 2.

For every field inside the builders add a method to set the field with a custom value.
It is important to return the test builder itself with every set method.

Method naming scheme: "with + {field name}"

Example:

```java
public OrderBuilder withCustomer(Customer customer){
    this.customer = customer;
    return this;
}

public OrderBuilder withId(Long orderId) {
        this.orderId = orderId;
        return this;
}
```

Remember that you don't need to use every method, because of the default values. If a given field is not important for the test you don't need to think about it while constructing the object.
## Step 3.

In our builder example, the builder consumes some arguments that are objects built by other builders. If we pass those builders as arguments instead of the constructed objects, we can simplify the code by removing the calls to build() methods.


For methods taking a object for which a builder is defined add the same method but taking the builder instead of the object.


Example:

```java

public OrderBuilder withCustomer(CustomerBuilder customerBuilder) {
        this.customer = customerBuilder.build();
        return this;
}
```

This reduces the code for creating test data. See test for step3.

## Step 4

Add factory methods for the builders to make creating new builders more verbose and shorter.

Example:
```java
public class OrderBuilder {
    // ...

    private OrderBuilder() {}

    public static OrderBuilder anOrder() {
        return new OrderBuilder();
    }

    // ...
}
```

Take advantage of the types and overload the "with" method.

Example from:

```java
public OrderBuilder withCustomer(CustomerBuilder orderItem){
        //...
        }

public OrderBuilder withOrderItem(OrderItemBuilder orderItem){
        //...    
        }
```

to:

```java
public OrderBuilder with(CustomerBuilder orderItem){
    //...
}

public OrderBuilder with(OrderItemBuilder orderItem){
        //...    
}
```

## Step 5

Reduce code when creating similar objects.
```java
Order orderWithSmallDiscount = anOrder()
    .with(anOrderItem().withName("Coffee mug").withQuantity(1))
    .with(anOrderItem().withName("Tea cup").withQuantity(1))
    .withDiscountRate(0.1)
    .build();
    
Order orderWithBigDiscount = anOrder()
    .with(anOrderItem().withName("Coffee mug").withQuantity(1))
    .with(anOrderItem().withName("Tea cup").withQuantity(1))
    .withDiscountRate(0.5)
    .build();
```

We just want to apply a different discountRate so we should repurpose the first builder.

```java
 OrderBuilder coffeeMugAndTeaCup = anOrder()
        .with(anOrderItem().withName("Coffee mug").withQuantity(1))
        .with(anOrderItem().withName("Tea cup").withQuantity(1));

Order orderWithSmallDiscount = coffeeMugAndTeaCup.withDiscountRate(0.1).build();
Order orderWithBigDiscount = coffeeMugAndTeaCup.withDiscountRate(0.5).build();
```
By reusing the common parts and naming the variables descriptively, the differences become much more apparent.

There is one pitfall in this approach, though. Let’s take a look at another example.

```java
OrderBuilder coffeeMugAndTeaCup = anOrder()
        .with(anOrderItem().withName("Coffee mug").withQuantity(1))
        .with(anOrderItem().withName("Tea cup").withQuantity(1));

Order orderWithDiscount = coffeeMugAndTeaCup.withDiscountRate(0.1).build();
Order orderWithCouponCode = coffeeMugAndTeaCup.withCouponCode("HALFOFF").build();
```

Now the second object also has a discount of 0.1 which we might not want.

We can fix this by using a but() method that creates a new instance of the builder.

```java
OrderBuilder coffeeMugAndTeaCup = anOrder()
        .with(anOrderItem().withName("Coffee mug").withQuantity(1))
        .with(anOrderItem().withName("Tea cup").withQuantity(1));

Order orderWithDiscount = coffeeMugAndTeaCup.but().withDiscountRate(0.1).build();
Order orderWithCouponCode = coffeeMugAndTeaCup.but().withCouponCode("HALFOFF").build();
```

Try to implement the but() method.