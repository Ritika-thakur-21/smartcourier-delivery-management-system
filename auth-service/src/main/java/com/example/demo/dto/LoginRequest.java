package com.example.demo.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Login request body")
public class LoginRequest {

    @Schema(description = "Email address", example = "raj@example.com")
    private String email;

    @Schema(description = "Password", example = "password123")
    private String password;

    // Getters
    public String getEmail() { return email; }
    public String getPassword() { return password; }

    // Setters
    public void setEmail(String email) { this.email = email; }
    public void setPassword(String password) { this.password = password; }
}