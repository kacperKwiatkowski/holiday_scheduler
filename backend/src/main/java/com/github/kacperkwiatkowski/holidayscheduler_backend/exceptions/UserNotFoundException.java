package com.github.kacperkwiatkowski.holidayscheduler_backend.exceptions;

import com.github.kacperkwiatkowski.holidayscheduler_backend.controllers.UserController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No such user")
public class UserNotFoundException extends RuntimeException {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    public UserNotFoundException(String message){
        super(message);
        logger.info(message);
    }
}
