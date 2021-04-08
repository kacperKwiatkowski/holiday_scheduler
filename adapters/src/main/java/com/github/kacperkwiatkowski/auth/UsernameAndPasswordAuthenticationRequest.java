package com.github.kacperkwiatkowski.auth;

class UsernameAndPasswordAuthenticationRequest {

    private String username;
    private String password;

    UsernameAndPasswordAuthenticationRequest() {
    }

    String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
