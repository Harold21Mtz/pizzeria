package com.platzi.pizzeria.service;

import com.platzi.pizzeria.entity.Order;
import com.platzi.pizzeria.entity.projection.OrderSummary;
import com.platzi.pizzeria.repository.OrderRepository;
import com.platzi.pizzeria.entity.dto.RandomPizza;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class OrderService {

    private static final String DELIVERY = "D";
    private static final String CARRYOUT = "C";
    private static final String ONSITE = "S";

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Order> getAllOrders(){
        return orderRepository.findAll();
    }

    public List<Order> getTodayOrdes(){
        LocalDateTime today = LocalDate.now().atTime(0,0);
        return orderRepository.findAllByOrderDateAfter(today);
    }

    public List<Order> getOutsideOrders(String method){
        String withoutspace = method.replace(" ", "");
        List<String> methods = Arrays.asList(withoutspace.split(","));
        return orderRepository.findAllByOrderMethodIgnoreCaseIn(methods);
    }

    public List<Order> getAllOrderByCustomer(UUID customerId){
        return orderRepository.findByCustomerOrder(customerId);
    }

    public OrderSummary getSummaryCustomer(UUID orderId){
        return orderRepository.findSummary(orderId);
    }

    @Transactional
    public boolean saveRandomOrder(RandomPizza pizza){
        return orderRepository.saveRandomOrder(pizza.getCustomerId(), pizza.getOrderMethod());
    }
}
