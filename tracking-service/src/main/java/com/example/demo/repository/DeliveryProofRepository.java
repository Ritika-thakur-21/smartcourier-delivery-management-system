package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.DeliveryProof;

public interface DeliveryProofRepository extends JpaRepository<DeliveryProof, Long> {

    List<DeliveryProof> findAllByDeliveryId(Long deliveryId);

    Optional<DeliveryProof> findByTrackingNumber(String trackingNumber);
}