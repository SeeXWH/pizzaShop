package com.example.pizzaShop.controller;

import com.example.pizzaShop.exeption.PizzaShopExeption;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExeptionController {

    @ExceptionHandler(PizzaShopExeption.class)
    public ResponseEntity<String> handlePizzaShopException(PizzaShopExeption ex) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        String message = ex.getMessage();
        if (ex.getMessage().contains("a user with that name or email already exists")) {
            status = HttpStatus.UNAUTHORIZED;
        } else if (ex.getMessage().contains("a user with that name or email already exists")) {
            status = HttpStatus.FORBIDDEN;
        } else if (ex.getMessage().contains("product already exists")) {
            status = HttpStatus.BAD_REQUEST;
        } else if (ex.getMessage().contains("there is no such product")) {
            status = HttpStatus.BAD_REQUEST;
        }
        return ResponseEntity.status(status).body(message);
    }
}

