package com.example.quiche_backend.dto;

import jakarta.validation.constraints.NotBlank;

/*
    @author: mihdjo
*/

public class LoginRequest {

    @NotBlank(message = "Username je obavezan.")
    private String username;

    @NotBlank(message = "Password je obavezan.")
    private String password;

    public LoginRequest() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}