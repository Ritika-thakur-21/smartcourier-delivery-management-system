package com.example.demo.controller;

import com.example.demo.dto.DeliveryRequest;
import com.example.demo.dto.DeliveryResponse;
import com.example.demo.service.DeliveryService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/deliveries")
public class DeliveryController {

    private final DeliveryService deliveryService;

    public DeliveryController(DeliveryService deliveryService) {
        this.deliveryService = deliveryService;
    }

    // ✅ CREATE DELIVERY
    @PostMapping
    public ResponseEntity<DeliveryResponse> create(
            @RequestHeader(value = "X-User-Email", required = false) String customerEmail,
            @RequestHeader("Authorization") String token,
            @RequestBody DeliveryRequest request) {

        if (customerEmail == null || customerEmail.isEmpty()) {
            throw new RuntimeException("❌ X-User-Email header missing");
        }

        return ResponseEntity.ok(
                deliveryService.createDelivery(request, customerEmail, token) // ✅ token pass
        );
    }

    // ✅ GET MY DELIVERIES
    @GetMapping("/my")
    public ResponseEntity<List<DeliveryResponse>> myDeliveries(
            @RequestHeader(value = "X-User-Email", required = false) String customerEmail) {

        if (customerEmail == null || customerEmail.isEmpty()) {
            throw new RuntimeException("❌ X-User-Email header missing");
        }

        return ResponseEntity.ok(
                deliveryService.getByCustomer(customerEmail)
        );
    }

    // ✅ GET ALL DELIVERIES
    @GetMapping
    public ResponseEntity<List<DeliveryResponse>> getAll() {
        return ResponseEntity.ok(deliveryService.getAllDeliveries());
    }

    // ✅ GET DELIVERY BY ID
    @GetMapping("/{id}")
    public ResponseEntity<DeliveryResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(deliveryService.getById(id));
    }

    // ✅ UPDATE STATUS
    @PutMapping("/{id}/status")
    public ResponseEntity<DeliveryResponse> updateStatus(
            @PathVariable Long id,
            @RequestParam String status) {

        return ResponseEntity.ok(
                deliveryService.updateStatus(id, status)
        );
    }
}