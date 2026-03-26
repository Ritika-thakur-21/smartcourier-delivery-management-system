package com.example.demo.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "JWT token response after login")
public class JwtResponse {

    @Schema(description = "JWT token")
    private String token;

    @Schema(description = "Token type", example = "Bearer")
    private String type = "Bearer";

    @Schema(description = "User email", example = "raj@example.com")
    private String email;

    @Schema(description = "User role", example = "CUSTOMER")
    private String role;

    @Schema(description = "User name", example = "Raj Kumar")
    private String name;

    public JwtResponse(String token, String email,
                       String role, String name) {
        this.token = token;
        this.email = email;
        this.role = role;
        this.name = name;
    }

    // Getters
    public String getToken() { return token; }
    public String getType() { return type; }
    public String getEmail() { return email; }
    public String getRole() { return role; }
    public String getName() { return name; }

    // Setters
    public void setToken(String token) { this.token = token; }
    public void setType(String type) { this.type = type; }
    public void setEmail(String email) { this.email = email; }
    public void setRole(String role) { this.role = role; }
    public void setName(String name) { this.name = name; }
}
