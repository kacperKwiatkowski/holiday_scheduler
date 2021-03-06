package com.github.kacperkwiatkowski.holidayscheduler_backend.exceptions;

public class IncorrectBodyException extends Exception {

    private String message;

    public static IncorrectBodyException createWith(String message){
        return new IncorrectBodyException(message);
    }

    public IncorrectBodyException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
