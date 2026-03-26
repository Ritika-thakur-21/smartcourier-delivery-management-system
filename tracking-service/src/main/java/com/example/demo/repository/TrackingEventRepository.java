package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.TrackingEvent;

public interface TrackingEventRepository extends JpaRepository<TrackingEvent, Long> {

    List<TrackingEvent> findByTrackingNumberOrderByEventTimeAsc(String trackingNumber);

    List<TrackingEvent> findByTrackingNumber(String trackingNumber);
}
