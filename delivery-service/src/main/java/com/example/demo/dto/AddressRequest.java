package com.example.demo.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Address details")
public class AddressRequest {

    @Schema(description = "Full name", example = "Raj Kumar")
    private String fullName;

    @Schema(description = "Phone number", example = "9876543210")
    private String phone;

    @Schema(description = "Street address", example = "12 MG Road")
    private String street;

    @Schema(description = "City", example = "Mumbai")
    private String city;

    @Schema(description = "State", example = "Maharashtra")
    private String state;

    @Schema(description = "Pincode", example = "400001")
    private String pincode;

    @Schema(description = "Country", example = "India")
    private String country;

    // Getters
    public String getFullName() { return fullName; }
    public String getPhone() { return phone; }
    public String getStreet() { return street; }
    public String getCity() { return city; }
    public String getState() { return state; }
    public String getPincode() { return pincode; }
    public String getCountry() { return country; }

    // Setters
    public void setFullName(String fullName) { this.fullName = fullName; }
    public void setPhone(String phone) { this.phone = phone; }
    public void setStreet(String street) { this.street = street; }
    public void setCity(String city) { this.city = city; }
    public void setState(String state) { this.state = state; }
    public void setPincode(String pincode) { this.pincode = pincode; }
    public void setCountry(String country) { this.country = country; }
}