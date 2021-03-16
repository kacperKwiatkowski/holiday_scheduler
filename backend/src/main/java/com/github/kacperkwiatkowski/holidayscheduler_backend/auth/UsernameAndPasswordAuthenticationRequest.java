package com.github.kacperkwiatkowski.holidayscheduler_backend.auth;

class UsernameAndPasswordAuthenticationRequest {

    private String username;
    private String password;

    UsernameAndPasswordAuthenticationRequest() {
    }

    String getUsername() {
        return username;
    }

    String getPassword() {
        return password;
    }
}
