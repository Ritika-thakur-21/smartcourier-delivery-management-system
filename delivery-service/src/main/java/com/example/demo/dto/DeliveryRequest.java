package com.example.demo.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Request body for creating a delivery")
public class DeliveryRequest {

    @Schema(description = "Sender address details", required = true)
    private AddressRequest senderAddress;

    @Schema(description = "Receiver address details", required = true)
    private AddressRequest receiverAddress;

    @Schema(description = "Customer email for notifications", example = "abc@gmail.com")
    private String customerEmail;   // 🔥 ADD THIS

    @Schema(
        description = "Type of service",
        example = "EXPRESS",
        allowableValues = {"DOMESTIC", "EXPRESS", "INTERNATIONAL"}
    )
    private String serviceType;

    @Schema(description = "Weight of parcel in kg", example = "2.5")
    private Double weight;

    @Schema(description = "Description of parcel contents", example = "Electronics")
    private String description;

    // getters & setters
    public AddressRequest getSenderAddress() { return senderAddress; }
    public AddressRequest getReceiverAddress() { return receiverAddress; }
    public String getCustomerEmail() { return customerEmail; }
    public String getServiceType() { return serviceType; }
    public Double getWeight() { return weight; }
    public String getDescription() { return description; }

    public void setSenderAddress(AddressRequest senderAddress) { this.senderAddress = senderAddress; }
    public void setReceiverAddress(AddressRequest receiverAddress) { this.receiverAddress = receiverAddress; }
    public void setCustomerEmail(String customerEmail) { this.customerEmail = customerEmail; }
    public void setServiceType(String serviceType) { this.serviceType = serviceType; }
    public void setWeight(Double weight) { this.weight = weight; }
    public void setDescription(String description) { this.description = description; }
}