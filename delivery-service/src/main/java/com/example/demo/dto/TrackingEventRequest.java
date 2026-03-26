package com.example.demo.dto;

public class TrackingEventRequest {

    private String trackingNumber;
    private String status;
    private String location;
    private String remarks;
    private String email;

    public TrackingEventRequest() {}

    public TrackingEventRequest(String trackingNumber, String status,
                                String location, String remarks, String email) {
        this.trackingNumber = trackingNumber;
        this.status = status;
        this.location = location;
        this.remarks = remarks;
        this.email = email;
    }

    // getters & setters

    public String getTrackingNumber() { return trackingNumber; }
    public void setTrackingNumber(String trackingNumber) { this.trackingNumber = trackingNumber; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public String getRemarks() { return remarks; }
    public void setRemarks(String remarks) { this.remarks = remarks; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}