package com.platzi.pizzeria.repository;

import com.platzi.pizzeria.entity.Order;
import com.platzi.pizzeria.entity.projection.OrderSummary;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface OrderRepository extends ListCrudRepository<Order, UUID> {

    List<Order> findAllByOrderDateAfter(LocalDateTime orderDate);

    List<Order> findAllByOrderMethodIgnoreCaseIn(List<String> strings);

    @Query(value = "Select * from main.pizza_order po where po.order_id = :customerId", nativeQuery = true)
    List<Order> findByCustomerOrder(@Param("customerId") UUID customerId);

    @Query(value = "SELECT po.order_id  AS orderId, cu.customer_name  AS customerName, po.order_date  AS orderDate, " +
            " po.order_total  AS orderTotal, STRING_AGG(pi.pizza_name , ', ') AS pizzaNames " +
            " FROM main.pizza_order po " +
            " INNER JOIN main.customer cu ON po.customer_id  = cu.customer_id " +
            " INNER JOIN main.order_item oi ON po.order_id  = oi.order_id " +
            " INNER JOIN main.pizza pi ON oi.pizza_id  = pi.pizza_id " +
            " WHERE po.order_id  = :orderId " +
            " GROUP BY po.order_id , cu.customer_id , po.order_date , po.order_total ", nativeQuery = true)
    OrderSummary findSummary(@Param("orderId") UUID orderId);

    @Procedure(value = "main.take_random_pizza_order", outputParameterName = "order_taken")
    boolean saveRandomOrder(@Param("customerId") UUID customer_id, @Param("orderMethod") String order_method);
}
