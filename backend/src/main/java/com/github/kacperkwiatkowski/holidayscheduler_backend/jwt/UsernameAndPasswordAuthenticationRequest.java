package com.github.kacperkwiatkowski.holidayscheduler_backend.jwt;

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
