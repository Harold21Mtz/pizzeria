package com.platzi.pizzeria.service.dto;

import lombok.Getter;

import java.util.UUID;

@Getter
public class UpdatePizzaPruiceDto {

    private UUID pizzaId;

    private Double pizzaPrice;
}
