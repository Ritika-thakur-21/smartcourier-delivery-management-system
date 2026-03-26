package com.example.demo.service;

import com.example.demo.client.TrackingClient;
import com.example.demo.dto.*;
import com.example.demo.entities.*;
import com.example.demo.enums.DeliveryStatus;
import com.example.demo.exception.DeliveryNotFoundException;
import com.example.demo.repository.DeliveryRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class DeliveryService {

    private final DeliveryRepository deliveryRepository;
    private final TrackingClient trackingClient;

    public DeliveryService(DeliveryRepository deliveryRepository,
                           TrackingClient trackingClient) {
        this.deliveryRepository = deliveryRepository;
        this.trackingClient = trackingClient;
    }

    // ✅ CREATE DELIVERY
    public DeliveryResponse createDelivery(DeliveryRequest request, String username,String token) {

        if (username == null || username.isEmpty()) {
            throw new RuntimeException("User missing");
        }

        Address sender = new Address();
        sender.setFullName(request.getSenderAddress().getFullName());
        sender.setPhone(request.getSenderAddress().getPhone());
        sender.setStreet(request.getSenderAddress().getStreet());
        sender.setCity(request.getSenderAddress().getCity());
        sender.setState(request.getSenderAddress().getState());
        sender.setPincode(request.getSenderAddress().getPincode());
        sender.setCountry(request.getSenderAddress().getCountry());

        Address receiver = new Address();
        receiver.setFullName(request.getReceiverAddress().getFullName());
        receiver.setPhone(request.getReceiverAddress().getPhone());
        receiver.setStreet(request.getReceiverAddress().getStreet());
        receiver.setCity(request.getReceiverAddress().getCity());
        receiver.setState(request.getReceiverAddress().getState());
        receiver.setPincode(request.getReceiverAddress().getPincode());
        receiver.setCountry(request.getReceiverAddress().getCountry());

        Delivery delivery = new Delivery();

        delivery.setTrackingNumber("TRK" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());

        delivery.setUsername(username); // 🔐 JWT user
        delivery.setCustomerEmail(request.getCustomerEmail()); // 📧 email

        delivery.setServiceType(request.getServiceType());
        delivery.setWeight(request.getWeight());
        delivery.setDescription(request.getDescription());
        delivery.setSenderAddress(sender);
        delivery.setReceiverAddress(receiver);
        delivery.setStatus(DeliveryStatus.BOOKED);

        Delivery saved = deliveryRepository.save(delivery);

        // 🔥 tracking call (same as tera code)
        try {
            TrackingEventRequest event = new TrackingEventRequest();
            event.setTrackingNumber(saved.getTrackingNumber());
            event.setStatus("BOOKED");
            event.setLocation(saved.getSenderAddress().getCity());
            event.setRemarks("Parcel Created");

            trackingClient.createEvent(token,event);
        } catch (Exception e) {
            System.out.println("❌ ERROR: " + e.getMessage());
        }

        return mapToResponse(saved);
    }

    // ✅ UPDATE STATUS
    public DeliveryResponse updateStatus(Long id, String status) {

        Delivery delivery = deliveryRepository.findById(id)
                .orElseThrow(() -> new DeliveryNotFoundException("Not found"));

        DeliveryStatus newStatus = DeliveryStatus.valueOf(status.toUpperCase());
        delivery.setStatus(newStatus);

        Delivery updated = deliveryRepository.save(delivery);

        try {
            TrackingEventRequest event = new TrackingEventRequest();
            event.setTrackingNumber(updated.getTrackingNumber());
            event.setStatus(updated.getStatus().name());
            event.setLocation(updated.getReceiverAddress().getCity());
            event.setRemarks("Status Updated");

            trackingClient.createEvent(status,event);

        } catch (Exception e) {
            System.out.println("❌ ERROR: " + e.getMessage());
        }

        return mapToResponse(updated);
    }
 // ✅ GET BY CUSTOMER
    public List<DeliveryResponse> getByCustomer(String email) {
        return deliveryRepository.findByCustomerEmail(email)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    // ✅ GET ALL DELIVERIES
    public List<DeliveryResponse> getAllDeliveries() {
        return deliveryRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    // ✅ GET BY ID
    public DeliveryResponse getById(Long id) {
        return mapToResponse(deliveryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Delivery not found")));
    }
    private DeliveryResponse mapToResponse(Delivery d) {
        DeliveryResponse r = new DeliveryResponse();
        r.setId(d.getId());
        r.setTrackingNumber(d.getTrackingNumber());
        r.setCustomerEmail(d.getCustomerEmail());
        r.setStatus(d.getStatus().name());
        r.setServiceType(d.getServiceType());
        r.setWeight(d.getWeight());
        r.setDescription(d.getDescription());
        r.setSenderCity(d.getSenderAddress().getCity());
        r.setReceiverCity(d.getReceiverAddress().getCity());
        r.setCreatedAt(d.getCreatedAt());
        r.setTrackingStatus(d.getStatus().name());
        return r;
    }
}