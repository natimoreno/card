package com.bank.card.ApiExceptions;

import org.springframework.javapoet.ClassName;

import java.util.logging.Logger;

public class ApiException extends Exception {

    private static final Logger LOGGER = Logger.getLogger(ClassName.class.getName());


    public ApiException(String message) {

        super(message);
        LOGGER.severe(message);
    }
}
