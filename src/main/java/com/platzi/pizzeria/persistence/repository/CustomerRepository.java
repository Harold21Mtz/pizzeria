package com.platzi.pizzeria.persistence.repository;

import com.platzi.pizzeria.persistence.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface CustomerRepository extends JpaRepository<Customer, UUID> {

    @Query(value = "SELECT c FROM  Customer c WHERE c.customerPhoneNumber= :customerPhone")
    Customer findByCustomerPhoneNumber(@Param("customerPhone") String customerPhone);

}
