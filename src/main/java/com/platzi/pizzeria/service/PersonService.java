package com.platzi.pizzeria.service;

import com.platzi.pizzeria.entity.Person;
import com.platzi.pizzeria.repository.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PersonService {

    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Person getPersonByPhone(String personPhone){
        log.info("Entré");
        return personRepository.findByPersonPhoneNumber(personPhone);
    }

}
