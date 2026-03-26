package com.example.demo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;

@Schema(description = "Delivery proof response")
public class DeliveryProofResponse {

    @Schema(description = "Proof ID", example = "1")
    private Long id;

    @Schema(description = "Delivery ID", example = "1")
    private Long deliveryId;

    @Schema(description = "Parcel tracking number", example = "TRK3A7B9C1D")
    private String trackingNumber;

    @Schema(description = "Receiver full name", example = "Priya Sharma")
    private String receiverName;

    @Schema(description = "Base64 encoded signature", example = "base64string")
    private String signature;

    @Schema(description = "Optional remarks", example = "Delivered at door")
    private String remarks;

    @Schema(description = "Delivery timestamp", example = "2026-03-23T12:00:00")
    private LocalDateTime deliveredAt;

    // Getters
    public Long getId() { return id; }
    public Long getDeliveryId() { return deliveryId; }
    public String getTrackingNumber() { return trackingNumber; }
    public String getReceiverName() { return receiverName; }
    public String getSignature() { return signature; }
    public String getRemarks() { return remarks; }
    public LocalDateTime getDeliveredAt() { return deliveredAt; }

    // Setters
    public void setId(Long id) { this.id = id; }
    public void setDeliveryId(Long deliveryId) { this.deliveryId = deliveryId; }
    public void setTrackingNumber(String trackingNumber) { this.trackingNumber = trackingNumber; }
    public void setReceiverName(String receiverName) { this.receiverName = receiverName; }
    public void setSignature(String signature) { this.signature = signature; }
    public void setRemarks(String remarks) { this.remarks = remarks; }
    public void setDeliveredAt(LocalDateTime deliveredAt) { this.deliveredAt = deliveredAt; }
}