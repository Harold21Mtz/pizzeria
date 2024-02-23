package com.platzi.pizzeria.controller;

import com.platzi.pizzeria.entity.Order;
import com.platzi.pizzeria.entity.projection.OrderSummary;
import com.platzi.pizzeria.service.OrderService;
import com.platzi.pizzeria.entity.dto.RandomPizza;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/")
    @Operation(description = "Get all orders")
    @ApiResponse(responseCode = "200", description = "Success")
    public ResponseEntity<List<Order>> getAllOrders(){
        return new ResponseEntity<>(orderService.getAllOrders(), HttpStatus.OK);
    }

    @GetMapping("/all_orders_today")
    @Operation(description = "Get all orders today")
    @ApiResponse(responseCode = "200", description = "Success")
    public ResponseEntity<List<Order>> getAllOrdersToday(){
        return new ResponseEntity<>(orderService.getTodayOrdes(), HttpStatus.OK);
    }

    @GetMapping("/orders_method/{method}")
    @Operation(description = "Get all ordersm by method")
    @ApiResponse(responseCode = "200", description = "Success")
    public ResponseEntity<List<Order>> getOrdersByMethod(@Parameter(description = "order method", required = true) @PathVariable("method") String method){
        return new ResponseEntity<>(orderService.getOutsideOrders(method), HttpStatus.OK);
    }

    @GetMapping("/order_by_customer/{id}")
    @Operation(description = "Get all orders by customer id")
    @ApiResponse(responseCode = "200", description = "Success")
    public ResponseEntity<List<Order>> getAllOrderByCustomer (@Parameter(description = "UUID of a customer", required = true) @PathVariable("id")UUID customerId){
        return new ResponseEntity<>(orderService.getAllOrderByCustomer(customerId), HttpStatus.OK);
    }

    @GetMapping("/order_summary/{id}")
    @Operation(description = "Get order summary of a customer")
    @ApiResponse(responseCode = "200", description = "Success")
    public ResponseEntity<OrderSummary> getSummayCustomer (@Parameter(description = "UUID of a order", required = true) @PathVariable("id")UUID orderId){
        return new ResponseEntity<>(orderService.getSummaryCustomer(orderId), HttpStatus.OK);
    }

    @PostMapping("/randomPizza")
    @Operation(description = "Add a pizza random")
    @ApiResponse(responseCode = "201", description = "Success")
    public ResponseEntity<Boolean> getRandomPizza(@Valid @RequestBody RandomPizza pizza){
        return new ResponseEntity<>(orderService.saveRandomOrder(pizza), HttpStatus.CREATED);
    }
}

