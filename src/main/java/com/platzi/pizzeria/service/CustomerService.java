package com.platzi.pizzeria.service;

import com.platzi.pizzeria.persistence.entity.Customer;
import com.platzi.pizzeria.persistence.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer getCustomerByPhone(String customerPhone){
        log.info("Entré");
        return customerRepository.findByCustomerPhoneNumber(customerPhone);
    }

}
