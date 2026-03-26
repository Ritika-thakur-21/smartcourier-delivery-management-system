package com.example.demo.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class TrackingEventProducer {

    private final RabbitTemplate rabbitTemplate;

    public TrackingEventProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendEvent(Map<String, Object> message) {

        System.out.println("📤 SENDING FULL OBJECT: " + message);

        rabbitTemplate.convertAndSend(
                "tracking_exchange",
                "tracking_routing",
                message
        );
    }
}