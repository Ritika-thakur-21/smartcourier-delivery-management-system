package com.example.demo.client;

import org.springframework.stereotype.Component;
import com.example.demo.dto.TrackingEventRequest;

@Component
public class TrackingClientFallback implements TrackingClient {

	@Override
	public void createEvent(String token, TrackingEventRequest request) {
	    System.out.println("⚠️ Tracking service down, fallback triggered");
	}
}