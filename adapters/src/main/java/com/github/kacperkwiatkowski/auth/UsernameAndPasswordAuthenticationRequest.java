package com.github.kacperkwiatkowski.auth;

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
