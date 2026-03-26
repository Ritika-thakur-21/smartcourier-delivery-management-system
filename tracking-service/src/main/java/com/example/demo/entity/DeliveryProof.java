package com.example.demo.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "delivery_proofs")
public class DeliveryProof {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long deliveryId;

    @Column(nullable = false)
    private String trackingNumber;

    @Column(nullable = false)
    private String receiverName;

    @Lob
    @Column(columnDefinition = "TEXT", nullable = false)
    private String signature;

    private String remarks;

    @Column(nullable = false, updatable = false)
    private LocalDateTime deliveredAt;

    @PrePersist
    public void prePersist() {
        this.deliveredAt = LocalDateTime.now();
    }

    // Getters & Setters

    public Long getId() { return id; }

    public Long getDeliveryId() { return deliveryId; }
    public void setDeliveryId(Long deliveryId) { this.deliveryId = deliveryId; }

    public String getTrackingNumber() { return trackingNumber; }
    public void setTrackingNumber(String trackingNumber) { this.trackingNumber = trackingNumber; }

    public String getReceiverName() { return receiverName; }
    public void setReceiverName(String receiverName) { this.receiverName = receiverName; }

    public String getSignature() { return signature; }
    public void setSignature(String signature) { this.signature = signature; }

    public String getRemarks() { return remarks; }
    public void setRemarks(String remarks) { this.remarks = remarks; }

    public LocalDateTime getDeliveredAt() { return deliveredAt; }
}