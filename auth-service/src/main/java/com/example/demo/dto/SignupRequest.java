package com.example.demo.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Signup request body")
public class SignupRequest {

    @Schema(description = "Full name", example = "Raj Kumar")
    private String name;

    @Schema(description = "Email address", example = "raj@example.com")
    private String email;

    @Schema(description = "Password", example = "password123")
    private String password;

    @Schema(description = "Phone number", example = "9876543210")
    private String phone;

    @Schema(
        description = "Role",
        example = "CUSTOMER",
        allowableValues = {"CUSTOMER", "ADMIN"}
    )
    private String role;

    // Getters
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public String getPhone() { return phone; }
    public String getRole() { return role; }

    // Setters
    public void setName(String name) { this.name = name; }
    public void setEmail(String email) { this.email = email; }
    public void setPassword(String password) { this.password = password; }
    public void setPhone(String phone) { this.phone = phone; }
    public void setRole(String role) { this.role = role; }
}
