package com.platzi.pizzeria.common.exception.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class EmailApiException extends RuntimeException{

    public EmailApiException(String msj) {
        super(msj);
    }
}
