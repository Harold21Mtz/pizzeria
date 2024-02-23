package com.platzi.pizzeria.controller;

import com.platzi.pizzeria.entity.Person;
import com.platzi.pizzeria.service.PersonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/person")
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/getByPhone/{phone}")
    @Operation(description = "Get a person by the phone number")
    @ApiResponse(responseCode = "200", description = "Success")
    public ResponseEntity<Person> getPersonByPhone(@Parameter(description = "Number of person", required = true) @PathVariable("phone") String phone){
        return new ResponseEntity<>(personService.getPersonByPhone(phone), HttpStatus.OK);
    }
}
