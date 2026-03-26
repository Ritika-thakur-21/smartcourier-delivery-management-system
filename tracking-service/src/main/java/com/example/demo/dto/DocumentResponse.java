package com.example.demo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;

@Schema(description = "Document upload response")
public class DocumentResponse {

    @Schema(description = "Document ID", example = "1")
    private Long id;

    @Schema(description = "Delivery ID", example = "1")
    private Long deliveryId;

    @Schema(description = "Original file name", example = "invoice.pdf")
    private String fileName;

    @Schema(description = "File MIME type", example = "application/pdf")
    private String fileType;

    @Schema(description = "Upload timestamp", example = "2026-03-23T12:00:00")
    private LocalDateTime uploadedAt;

    // Getters
    public Long getId() { return id; }
    public Long getDeliveryId() { return deliveryId; }
    public String getFileName() { return fileName; }
    public String getFileType() { return fileType; }
    public LocalDateTime getUploadedAt() { return uploadedAt; }

    // Setters
    public void setId(Long id) { this.id = id; }
    public void setDeliveryId(Long deliveryId) { this.deliveryId = deliveryId; }
    public void setFileName(String fileName) { this.fileName = fileName; }
    public void setFileType(String fileType) { this.fileType = fileType; }
    public void setUploadedAt(LocalDateTime uploadedAt) { this.uploadedAt = uploadedAt; }
}