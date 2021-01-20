package com.github.kacperkwiatkowski.holidayscheduler_backend.exceptions;

import com.github.kacperkwiatkowski.holidayscheduler_backend.controllers.UserController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class EntityNotFoundException extends Exception {

    private String message;

    public static EntityNotFoundException createWith(String message){
        return new EntityNotFoundException(message);
    }

    public EntityNotFoundException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
