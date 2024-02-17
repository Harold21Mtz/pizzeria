package com.platzi.pizzeria.persistence.repository;

import com.platzi.pizzeria.persistence.entity.Pizza;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.ListPagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PizzaPagSortRepository extends ListPagingAndSortingRepository<Pizza, UUID> {

    Page<Pizza> findByPizzaAvailableTrueOrderByPizzaPrice(Pageable pageable);

}
