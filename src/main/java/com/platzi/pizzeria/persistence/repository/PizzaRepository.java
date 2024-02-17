package com.platzi.pizzeria.persistence.repository;

import com.platzi.pizzeria.persistence.entity.Pizza;
import com.platzi.pizzeria.service.dto.UpdatePizzaPruiceDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PizzaRepository extends JpaRepository<Pizza, UUID> {

    List<Pizza> findAllByPizzaAvailableTrueOrderByPizzaPriceDesc();

    Pizza findPizzaByPizzaAvailableTrueAndPizzaNameIgnoreCase(String pizzaName);

    List<Pizza> findAllByPizzaAvailableTrueAndPizzaDescriptionContainingIgnoreCase(String pizzaDescription);

    List<Pizza> findAllByPizzaAvailableTrueAndPizzaDescriptionNotContainingIgnoreCase(String pizzaDescription);

    int countAllByPizzaVeganTrue();

    Optional<Pizza> findFirstByPizzaAvailableTrueAndPizzaNameIgnoreCase(String pizzaName);

    List<Pizza> findTop3ByPizzaAvailableTrueAndAndPizzaPriceLessThanEqualOrderByPizzaPriceDesc(Double pizzaPrice);

//    @Query(value = "update main.pizza set pizza_price = :pizzaPrice where pizza_id =: pizzaId", nativeQuery = true)
//    void updatepizzaPrice(@Param("pizzaID") UUID pizzaId, @Param("pizzaPrice") Double pizzaPrice);

    @Query(value = " UPDATE main.pizza " +
            " SET pizza_price = :#{#newpizzaPrice.pizzaPrice} " +
            " WHERE pizza_id = :#{#newpizzaPrice.pizzaId} ", nativeQuery = true)
    @Modifying
    void updatepizzaPrice(@Param("newpizzaPrice") UpdatePizzaPruiceDto newpizzaPrice);
}
