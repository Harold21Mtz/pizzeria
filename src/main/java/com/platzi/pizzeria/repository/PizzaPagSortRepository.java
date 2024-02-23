package com.platzi.pizzeria.repository;

import com.platzi.pizzeria.entity.Pizza;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.ListPagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PizzaPagSortRepository extends ListPagingAndSortingRepository<Pizza, UUID> {

    Page<Pizza> findByPizzaAvailableTrueOrderByPizzaPrice(Pageable pageable);

}
