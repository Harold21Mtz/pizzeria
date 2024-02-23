package com.platzi.pizzeria.service;

import com.platzi.pizzeria.common.exception.service.EmailApiException;
import com.platzi.pizzeria.common.exception.service.ResourceNotFoundException;
import com.platzi.pizzeria.entity.Pizza;
import com.platzi.pizzeria.repository.PizzaPagSortRepository;
import com.platzi.pizzeria.repository.PizzaRepository;
import com.platzi.pizzeria.entity.dto.UpdatePizzaPruiceDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class PizzaService {

    private final JdbcTemplate jdbcTemplate;

    private final PizzaRepository pizzaRepository;
    private final PizzaPagSortRepository pizzaPagSortRepository;

    public PizzaService(JdbcTemplate jdbcTemplate, PizzaRepository pizzaRepository, PizzaPagSortRepository pizzaPagSortRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.pizzaRepository = pizzaRepository;
        this.pizzaPagSortRepository = pizzaPagSortRepository;
    }

    public Page<Pizza> getAllPizzas(int page, int elements){
//        return jdbcTemplate.query("SELECT * FROM main.pizza", new BeanPropertyRowMapper<>(Pizza.class));
        Pageable pageable = PageRequest.of(page, elements);
        return pizzaPagSortRepository.findAll(pageable);
    }

    public Pizza getPizzaById(UUID pizzaId){
        return pizzaRepository.findById(pizzaId).orElseThrow(() -> new ResourceNotFoundException("No existe la pizza buscada"));
    }

    public Pizza addPizza(Pizza pizza){
        return pizzaRepository.save(pizza);
    }

    public void updatePizza(UUID pizzaId, Pizza updatedPizza){
        pizzaRepository.findById(pizzaId).map(pizza -> {
            pizza.setPizzaName(updatedPizza.getPizzaName());
                    pizza.setPizzaDescription(updatedPizza.getPizzaDescription());
                    pizza.setPizzaPrice(updatedPizza.getPizzaPrice());
                    pizza.setPizzaVegetarian(updatedPizza.getPizzaVegetarian());
                    pizza.setPizzaVegan(updatedPizza.getPizzaVegan());
                    pizza.setPizzaAvailable(updatedPizza.getPizzaAvailable());
                    return pizzaRepository.save(pizza);
                })
                .orElseThrow(() -> new ResourceNotFoundException("No existe la pizza a actualizar"));
    }

    public void deletePizza(UUID pizzaId){
        Pizza pizza = pizzaRepository.findById(pizzaId).orElseThrow(() -> new ResourceNotFoundException("No existe la pizza a borrar"));
        pizzaRepository.delete(pizza);
    }

    public Page<Pizza> getAvailablePizza(int page, int elements, String sortBy, String sortDirection){
        log.info("count of number pizzas vegan {}", pizzaRepository.countAllByPizzaVeganTrue());

        Pageable pageable = PageRequest.of(page, elements, Sort.by(Sort.Direction.fromString(sortDirection), sortBy));
        return pizzaPagSortRepository.findByPizzaAvailableTrueOrderByPizzaPrice(pageable);
//        return pizzaRepository.findAllByPizzaAvailableTrueOrderByPizzaPriceDesc();
    }

    public Pizza getPizzaByName(String pizzaName){
        return pizzaRepository.findPizzaByPizzaAvailableTrueAndPizzaNameIgnoreCase(pizzaName);
    }

    public List<Pizza> getPizzaByIngredientContaining(String pizzaDescription){
        return pizzaRepository.findAllByPizzaAvailableTrueAndPizzaDescriptionContainingIgnoreCase(pizzaDescription);
    }

    public List<Pizza> getPizzaByIngredientNotContaining(String pizzaDescription){
        return pizzaRepository.findAllByPizzaAvailableTrueAndPizzaDescriptionNotContainingIgnoreCase(pizzaDescription);
    }

    public Pizza getFirtsPizzaByName(String pizzaName){
        return pizzaRepository.findFirstByPizzaAvailableTrueAndPizzaNameIgnoreCase(pizzaName).orElseThrow(() -> new ResourceNotFoundException("No existe la pizza por el nombre ingresado"));
    }

    public List<Pizza> getPizzaByPriceMostCheapes(Double pricePizza){
        return pizzaRepository.findTop3ByPizzaAvailableTrueAndAndPizzaPriceLessThanEqualOrderByPizzaPriceDesc(pricePizza);
    }

    @Transactional(noRollbackFor = EmailApiException.class)
    public void updatePizzaPrice(UpdatePizzaPruiceDto dto){
        pizzaRepository.findById(dto.getPizzaId()).orElseThrow(() -> new ResourceNotFoundException("No existe la pizza a borrar"));
        pizzaRepository.updatepizzaPrice(dto);
        //sendEmail();
    }

    private void sendEmail() {
        throw new EmailApiException("Se totió to esta vaina");
    }
}
