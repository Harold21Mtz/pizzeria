package com.platzi.pizzeria.persistence.projection;

import java.time.LocalDateTime;
import java.util.UUID;

public interface OrderSummary {

    UUID getOrderId();

    String getCustomerName();

    LocalDateTime getOrderDate();

    Double getOrderTotal();

    String getPizzaNames();

}
