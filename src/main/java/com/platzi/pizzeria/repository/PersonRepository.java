package com.platzi.pizzeria.repository;

import com.platzi.pizzeria.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface PersonRepository extends JpaRepository<Person, UUID> {

    @Query(value = "SELECT p FROM  Person p WHERE p.personPhoneNumber = :personPhone")
    Person findByPersonPhoneNumber(@Param("personPhone") String personPhone);

}
