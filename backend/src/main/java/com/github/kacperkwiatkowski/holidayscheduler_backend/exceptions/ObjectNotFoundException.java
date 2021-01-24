package com.github.kacperkwiatkowski.holidayscheduler_backend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ObjectNotFoundException extends RuntimeException {

    private String message;

    public static ObjectNotFoundException createWith(String message){
        return new ObjectNotFoundException(message);
    }

    public ObjectNotFoundException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
