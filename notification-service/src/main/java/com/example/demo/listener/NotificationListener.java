package com.example.demo.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.example.demo.dto.TrackingEventDTO;
import com.example.demo.service.EmailService;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

@Service
public class NotificationListener {

    private final EmailService emailService;
    private final ObjectMapper objectMapper;

    public NotificationListener(EmailService emailService,
                                ObjectMapper objectMapper) {
        this.emailService = emailService;
        this.objectMapper = objectMapper;
    }

    @RabbitListener(queues = "tracking_queue")
    public void receiveEvent(TrackingEventDTO event) {

        System.out.println("📥 RECEIVED EVENT: " + event.getTrackingNumber());

        emailService.sendEmail(
                event.getEmail(),
                "🚚 Delivery Update",
                "Tracking Number: " + event.getTrackingNumber() + "\n" +
                "Status: " + event.getStatus() + "\n" +
                "Location: " + event.getLocation() + "\n" +
                "Remarks: " + event.getRemarks()
        );
    }
}