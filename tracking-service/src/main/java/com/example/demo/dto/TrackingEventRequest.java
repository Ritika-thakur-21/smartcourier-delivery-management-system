package com.example.demo.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Request body for creating a tracking event")
public class TrackingEventRequest {

    @Schema(description = "Parcel tracking number", example = "TRK3A7B9C1D")
    private String trackingNumber;

    @Schema(
        description = "Current delivery status",
        example = "BOOKED",
        allowableValues = {
            "DRAFT", "BOOKED", "PICKED_UP", "IN_TRANSIT",
            "OUT_FOR_DELIVERY", "DELIVERED",
            "DELAYED", "FAILED", "RETURNED"
        }
    )
    private String status;

    @Schema(description = "Current location of parcel", example = "Mumbai")
    private String location;

    @Schema(description = "Optional remarks", example = "Parcel booked successfully")
    private String remarks;
    public TrackingEventRequest() {}
    // Getters
    public String getTrackingNumber() { return trackingNumber; }
    public String getStatus() { return status; }
    public String getLocation() { return location; }
    public String getRemarks() { return remarks; }

    // Setters
    public void setTrackingNumber(String trackingNumber) { this.trackingNumber = trackingNumber; }
    public void setStatus(String status) { this.status = status; }
    public void setLocation(String location) { this.location = location; }
    public void setRemarks(String remarks) { this.remarks = remarks; }
}