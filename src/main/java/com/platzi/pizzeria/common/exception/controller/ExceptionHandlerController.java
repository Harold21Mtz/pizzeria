package com.platzi.pizzeria.common.exception.controller;

import com.platzi.pizzeria.common.exception.service.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ServerErrorException;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String,String> resourceException(ResourceNotFoundException ex){
        Map<String,String> map = new HashMap<>();
        map.put("error", "Bad Request");
        map.put("message", ex.getMessage());
        log.warn("ResourceNotFoundException [{}]",ex.getMessage());
        return map;
    }

    @ExceptionHandler(ServerErrorException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String,String> serverException(ServerErrorException ex){
        Map<String,String> map = new HashMap<>();
        map.put("error", "Internal Server Error");
        map.put("message", ex.getMessage());
        log.warn("ServerErrorException [{}]",ex.getMessage());
        return map;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String,String> methodException(MethodArgumentNotValidException ex){
        Map<String,String> map = new HashMap<>();
        map.put("error", "Bad Request");
        map.put("message", ex.getMessage());
        log.warn("MethodArgumentNotValidException [{}]",ex.getMessage());
        return map;
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String,String> illegalException(IllegalArgumentException ex){
        Map<String,String> map = new HashMap<>();
        map.put("error", "Bad Request");
        map.put("message", ex.getMessage());
        log.warn("IllegalArgumentException [{}]",ex.getMessage());
        return map;
    }
}
