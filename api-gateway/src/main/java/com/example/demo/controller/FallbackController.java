package com.example.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.Map;

@RestController
@RequestMapping("/fallback")
public class FallbackController {

    @GetMapping("/delivery")
    public Mono<ResponseEntity<Map<String, String>>> deliveryFallback() {
        return Mono.just(ResponseEntity
                .status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(Map.of(
                    "status", "503",
                    "error", "Service Unavailable",
                    "message", "Delivery service is currently down."
                )));
    }

    @GetMapping("/tracking")
    public Mono<ResponseEntity<Map<String, String>>> trackingFallback() {
        return Mono.just(ResponseEntity
                .status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(Map.of(
                    "status", "503",
                    "error", "Service Unavailable",
                    "message", "Tracking service is currently down."
                )));
    }
}