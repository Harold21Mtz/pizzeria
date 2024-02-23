package com.platzi.pizzeria.audit;

import com.platzi.pizzeria.entity.Pizza;
import jakarta.persistence.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.SerializationUtils;

@Slf4j
public class AuditPizzaListener {

    private Pizza pizzaEntity;

    @PostLoad
    public void postLoad(Pizza pizza) {
//        log.info("POST LOAD");
        System.out.println("POST LOAD");
        pizzaEntity = SerializationUtils.clone(pizza);
    }

    @PostPersist
    @PostUpdate
    public void onPostPersist(Pizza pizza) {
//        log.info("POST PERSIT OR UPDATE");
//        log.info("OLD VALUE: {}", pizzaEntity.toString());
//        log.info("NEW VALUE: {}", pizza.toString());
        System.out.println("POST PERSIT OR UPDATE");
        System.out.println("OLD VALUE: " + this.pizzaEntity.toString());
        System.out.println("NEW VALUE: " + pizza.toString());
    }

    @PreRemove
    public void preDelete(Pizza pizzaEntity) {
//        log.info("PRE DELETE");
//        log.info("OLD VALUE: {}", pizzaEntity.toString());
        System.out.println("PRE DELETE");
        System.out.println("OLD VALUE: " + pizzaEntity);
    }
}
