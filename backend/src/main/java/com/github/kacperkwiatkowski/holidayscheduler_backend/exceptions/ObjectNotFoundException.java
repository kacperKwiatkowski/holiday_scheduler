package com.github.kacperkwiatkowski.holidayscheduler_backend.exceptions;

public class ObjectNotFoundException extends Exception {

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
