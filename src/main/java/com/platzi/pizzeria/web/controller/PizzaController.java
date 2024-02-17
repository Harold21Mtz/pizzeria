package com.platzi.pizzeria.web.controller;

import com.platzi.pizzeria.persistence.entity.Pizza;
import com.platzi.pizzeria.service.PizzaService;
import com.platzi.pizzeria.service.dto.UpdatePizzaPruiceDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/pizza")
public class PizzaController {

    private final PizzaService pizzaService;

    public PizzaController(PizzaService pizzaService) {
        this.pizzaService = pizzaService;
    }

    @GetMapping("/")
    @Operation(description = "Get all pizzas")
    @ApiResponse(responseCode = "200", description = "Success")
    public ResponseEntity<Page<Pizza>> getAll(@RequestParam(defaultValue = "0") int page,
                                              @RequestParam(defaultValue = "8") int elements) {
        return new ResponseEntity<>(this.pizzaService.getAllPizzas(page, elements), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(description = "Get pizza by Id")
    @ApiResponse(responseCode = "200", description = "Success")
    public ResponseEntity<Pizza> getPizzaById(@Parameter(description = "UUID of an pizza", required = true) @PathVariable("id") UUID pizzaId) {
        return new ResponseEntity<>(pizzaService.getPizzaById(pizzaId), HttpStatus.OK);
    }

    @PostMapping("/add_pizza")
    @Operation(description = "Add a pizza")
    @ApiResponse(responseCode = "201", description = "Created")
    public ResponseEntity<Pizza> addPizza(@RequestBody Pizza pizza) {
        return new ResponseEntity<>(pizzaService.addPizza(pizza), HttpStatus.CREATED);
    }

    @PutMapping("/update_pizza/{id}")
    @Operation(description = "Update a pizza")
    @ApiResponse(responseCode = "204", description = "Updated")
    public ResponseEntity<HttpStatus> updatePizza(@Parameter(description = "UUID of an pizza", required = true) @PathVariable("id") UUID pizzaId, @RequestBody Pizza pizza) {
        pizzaService.updatePizza(pizzaId, pizza);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    @Operation(description = "Delete pizza by Id")
    @ApiResponse(responseCode = "204", description = "No content")
    public ResponseEntity<HttpStatus> deletePizza(@Parameter(description = "UUID of a pizza", required = true) @PathVariable("id") UUID pizzaId) {
        pizzaService.deletePizza(pizzaId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/available")
    @Operation(description = "Get all pizzas available")
    @ApiResponse(responseCode = "200", description = "Success")
    public ResponseEntity<Page<Pizza>> getAllPizzasAvailable(@RequestParam(defaultValue = "0") int page,
                                                             @RequestParam(defaultValue = "8") int elements,
                                                             @RequestParam(defaultValue = "pizzaPrice") String sortBy,
                                                             @RequestParam(defaultValue = "ASC") String sortDirection
    ) {
        return new ResponseEntity<>(this.pizzaService.getAvailablePizza(page, elements, sortBy, sortDirection), HttpStatus.OK);
    }

    @GetMapping("/pizza_name/{name}")
    @Operation(description = "Get pizza by name")
    @ApiResponse(responseCode = "200", description = "Success")
    public ResponseEntity<Pizza> getPizzaByName(@Parameter(description = "Name of a pizza", required = true) @PathVariable("name") String name) {
        return new ResponseEntity<>(pizzaService.getPizzaByName(name), HttpStatus.OK);
    }

    @GetMapping("/with/{ingredient}")
    @Operation(description = "Get a pizza by ingredient")
    @ApiResponse(responseCode = "200", description = "Success")
    public ResponseEntity<List<Pizza>> getPizzaByIngedientContaining(@Parameter(description = "Ingredient of pizza", required = true) @PathVariable("ingredient") String ingredient) {
        return new ResponseEntity<>(pizzaService.getPizzaByIngredientContaining(ingredient), HttpStatus.OK);
    }

    @GetMapping("/without/{ingredient}")
    @Operation(description = "Get a pizza by ingredient no containing")
    @ApiResponse(responseCode = "200", description = "Success")
    public ResponseEntity<List<Pizza>> getPizzaByIngedientNotContaining(@Parameter(description = "Ingredient of pizza", required = true) @PathVariable("ingredient") String ingredient) {
        return new ResponseEntity<>(pizzaService.getPizzaByIngredientNotContaining(ingredient), HttpStatus.OK);
    }

    @GetMapping("/firt_pizza_name/{name}")
    @Operation(description = "Get pizza by name")
    @ApiResponse(responseCode = "200", description = "Success")
    public ResponseEntity<Pizza> getFirtsPizzaByName(@Parameter(description = "Name of a pizza", required = true) @PathVariable("name") String name) {
        return new ResponseEntity<>(pizzaService.getFirtsPizzaByName(name), HttpStatus.OK);
    }

    @GetMapping("/mostCheapes/{price}")
    @Operation(description = "Get a pizza most cheapes")
    @ApiResponse(responseCode = "200", description = "Success")
    public ResponseEntity<List<Pizza>> getPizzaByPriceMostCheapes(@Parameter(description = "price of pizza", required = true) @PathVariable("price") Double pizzaPrice) {
        return new ResponseEntity<>(pizzaService.getPizzaByPriceMostCheapes(pizzaPrice), HttpStatus.OK);
    }

    @PutMapping("/updatePrice")
    @Operation(description = "Updatethe price of a pizza")
    @ApiResponse(responseCode = "204", description = "Updated")
    public ResponseEntity<HttpStatus> updatePizzaPrice(@Valid @RequestBody UpdatePizzaPruiceDto dto){
        pizzaService.updatePizzaPrice(dto);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

