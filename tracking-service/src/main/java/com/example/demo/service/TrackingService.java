package com.example.demo.service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dto.*;
import com.example.demo.entity.*;
import com.example.demo.exception.*;
import com.example.demo.repository.*;

@Service
public class TrackingService {

    private final TrackingEventRepository trackingEventRepository;
    private final DocumentRepository documentRepository;
    private final DeliveryProofRepository deliveryProofRepository;
    private final RabbitTemplate rabbitTemplate;

    public TrackingService(TrackingEventRepository trackingEventRepository,
                           DocumentRepository documentRepository,
                           DeliveryProofRepository deliveryProofRepository,
                           RabbitTemplate rabbitTemplate) {
        this.trackingEventRepository = trackingEventRepository;
        this.documentRepository = documentRepository;
        this.deliveryProofRepository = deliveryProofRepository;
        this.rabbitTemplate = rabbitTemplate;
    }

    // ✅ CREATE EVENT
    public void createEvent(TrackingEventRequest request) {

        TrackingEvent event = new TrackingEvent();
        event.setTrackingNumber(request.getTrackingNumber());
        event.setStatus(request.getStatus());
        event.setLocation(request.getLocation());
        event.setRemarks(request.getRemarks());

        trackingEventRepository.save(event);

        // 🔥 DTO for notification
        TrackingEventDTO dto = new TrackingEventDTO();
        dto.setTrackingNumber(request.getTrackingNumber());
        dto.setStatus(request.getStatus());
        dto.setLocation(request.getLocation());
        dto.setRemarks(request.getRemarks());
        dto.setEmail("thakurritika361@gmail.com");

        try {
            System.out.println("🔥 Sending to RabbitMQ: " + dto.getTrackingNumber());

            rabbitTemplate.convertAndSend(
                    "tracking_exchange",     // ✅ FIXED
                    "tracking_routing",      // ✅ FIXED
                    dto
            );

            System.out.println("✅ Message sent to RabbitMQ");

        } catch (Exception e) {
            System.out.println("❌ RabbitMQ ERROR: " + e.getMessage());
        }
    }

    // ✅ GET EVENTS
    public List<TrackingEventResponse> getEvents(String trackingNumber) {
        return trackingEventRepository
                .findByTrackingNumberOrderByEventTimeAsc(trackingNumber)
                .stream()
                .map(this::mapToEventResponse)
                .collect(Collectors.toList());
    }

    // ✅ UPLOAD DOCUMENT
    public DocumentResponse saveDocument(MultipartFile file, Long deliveryId) {

        if (file == null || file.isEmpty()) {
            throw new RuntimeException("File is empty");
        }

        try {
            Document document = new Document();
            document.setDeliveryId(deliveryId);
            document.setFileName(file.getOriginalFilename());
            document.setFileType(file.getContentType());
            document.setFileData(file.getBytes());

            return mapToDocumentResponse(documentRepository.save(document));

        } catch (IOException e) {
            throw new RuntimeException("Upload failed: " + e.getMessage());
        }
    }

    public List<DocumentResponse> getDocuments(Long deliveryId) {
        return documentRepository.findByDeliveryId(deliveryId)
                .stream()
                .map(this::mapToDocumentResponse)
                .collect(Collectors.toList());
    }

    // ✅ SAVE PROOF
    public DeliveryProofResponse saveProof(Long deliveryId,
                                           String trackingNumber,
                                           String receiverName,
                                           String signature,
                                           String remarks) {

        List<DeliveryProof> existing =
                deliveryProofRepository.findAllByDeliveryId(deliveryId);

        if (!existing.isEmpty()) {
            throw new DuplicateProofException("Proof already exists");
        }

        DeliveryProof proof = new DeliveryProof();
        proof.setDeliveryId(deliveryId);
        proof.setTrackingNumber(trackingNumber);
        proof.setReceiverName(receiverName);
        proof.setSignature(signature);
        proof.setRemarks(remarks);

        return mapToProofResponse(deliveryProofRepository.save(proof));
    }

    // ✅ GET PROOF
    public DeliveryProofResponse getProofByDeliveryId(Long deliveryId) {

        List<DeliveryProof> proofs =
                deliveryProofRepository.findAllByDeliveryId(deliveryId);

        if (proofs.isEmpty()) {
            throw new TrackingNotFoundException("Proof not found");
        }

        return mapToProofResponse(proofs.get(0));
    }

    // 🔁 MAPPERS

    private DocumentResponse mapToDocumentResponse(Document doc) {
        DocumentResponse res = new DocumentResponse();
        res.setId(doc.getId());
        res.setDeliveryId(doc.getDeliveryId());
        res.setFileName(doc.getFileName());
        res.setFileType(doc.getFileType());
        res.setUploadedAt(doc.getUploadedAt());
        return res;
    }

    private TrackingEventResponse mapToEventResponse(TrackingEvent event) {
        TrackingEventResponse res = new TrackingEventResponse();
        res.setId(event.getId());
        res.setTrackingNumber(event.getTrackingNumber());
        res.setStatus(event.getStatus());
        res.setLocation(event.getLocation());
        res.setRemarks(event.getRemarks());
        res.setEventTime(event.getEventTime());
        return res;
    }

    private DeliveryProofResponse mapToProofResponse(DeliveryProof proof) {
        DeliveryProofResponse res = new DeliveryProofResponse();
        res.setId(proof.getId());
        res.setDeliveryId(proof.getDeliveryId());
        res.setTrackingNumber(proof.getTrackingNumber());
        res.setReceiverName(proof.getReceiverName());
        res.setSignature(proof.getSignature());
        res.setRemarks(proof.getRemarks());
        res.setDeliveredAt(proof.getDeliveredAt());
        return res;
    }
}