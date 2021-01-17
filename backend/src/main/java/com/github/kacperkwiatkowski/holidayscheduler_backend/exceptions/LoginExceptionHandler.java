package com.github.kacperkwiatkowski.holidayscheduler_backend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="No such Order")
public class LoginExceptionHandler {
}
