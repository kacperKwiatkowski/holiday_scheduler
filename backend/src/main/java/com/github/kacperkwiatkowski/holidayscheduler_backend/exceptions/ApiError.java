package com.github.kacperkwiatkowski.holidayscheduler_backend.exceptions;

import java.util.List;

class ApiError {
    private List<String> errors;

    public ApiError(List<String> errors) {
        this.errors = errors;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }
}