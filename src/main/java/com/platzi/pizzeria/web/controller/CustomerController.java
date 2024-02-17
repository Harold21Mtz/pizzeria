package com.platzi.pizzeria.web.controller;

import com.platzi.pizzeria.persistence.entity.Customer;
import com.platzi.pizzeria.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/getByPhone/{phone}")
    @Operation(description = "Get a customer by the phone number")
    @ApiResponse(responseCode = "200", description = "Success")
    public ResponseEntity<Customer> getCustomerByPhone(@Parameter(description = "Number of customer", required = true) @PathVariable("phone") String phone){
        return new ResponseEntity<>(customerService.getCustomerByPhone(phone), HttpStatus.OK);
    }
}
