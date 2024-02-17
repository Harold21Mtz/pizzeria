package com.platzi.pizzeria.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.UUID;

@Getter
public class RandomPizza {

    private UUID customerId;

    private String orderMethod;
}
