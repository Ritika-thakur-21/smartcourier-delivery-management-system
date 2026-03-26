package com.example.demo.controller;

import com.example.demo.dto.*;
import com.example.demo.entity.Document;
import com.example.demo.repository.DocumentRepository;
import com.example.demo.service.TrackingService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class TrackingControllerTest {

    private MockMvc mockMvc;

    @Mock
    private TrackingService trackingService;

    @Mock
    private DocumentRepository documentRepository;

    @InjectMocks
    private TrackingController trackingController;

    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders
                .standaloneSetup(trackingController)
                .build();
    }

    // ✅ UPLOAD DOCUMENT
    @Test
    void testUploadDocument() throws Exception {

        MockMultipartFile file = new MockMultipartFile(
                "file",
                "test.pdf",
                "application/pdf",
                "dummy".getBytes()
        );

        Mockito.when(trackingService.saveDocument(Mockito.any(), Mockito.eq(1L)))
                .thenReturn(new DocumentResponse());

        mockMvc.perform(multipart("/tracking/documents/upload")
                        .file(file)
                        .param("deliveryId", "1"))
                .andExpect(status().isOk());
    }

    // ✅ GET DOCUMENTS
    @Test
    void testGetDocuments() throws Exception {

        Mockito.when(trackingService.getDocuments(1L))
                .thenReturn(List.of(new DocumentResponse()));

        mockMvc.perform(get("/tracking/documents/1"))
                .andExpect(status().isOk());
    }

    // ✅ DOWNLOAD DOCUMENT
    @Test
    void testDownloadDocument() throws Exception {

        Document doc = new Document();
//        doc.setId(1L);
        doc.setFileName("file.pdf");
        doc.setFileType("application/pdf");
        doc.setFileData("data".getBytes());

        Mockito.when(documentRepository.findById(1L))
                .thenReturn(Optional.of(doc));

        mockMvc.perform(get("/tracking/documents/download/1"))
                .andExpect(status().isOk())
                .andExpect(header().exists("Content-Disposition"));
    }

    // ❌ DOWNLOAD NOT FOUND
//    @Test
//    void testDownloadDocument_NotFound() throws Exception {
//
//        Mockito.when(documentRepository.findById(1L))
//                .thenReturn(Optional.empty());
//
//        mockMvc.perform(get("/tracking/documents/download/1"))
//                .andExpect(status().isInternalServerError());
//    }

    // ✅ CREATE EVENT
    @Test
    void testCreateEvent() throws Exception {

        TrackingEventRequest request = new TrackingEventRequest();

        mockMvc.perform(post("/tracking/events")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());

        Mockito.verify(trackingService).createEvent(Mockito.any());
    }

    // ✅ TRACK
    @Test
    void testTrack() throws Exception {

        Mockito.when(trackingService.getEvents("TRK123"))
                .thenReturn(List.of(new TrackingEventResponse()));

        mockMvc.perform(get("/tracking/TRK123"))
                .andExpect(status().isOk());
    }

    // ✅ SUBMIT PROOF
    @Test
    void testSubmitProof() throws Exception {

        Mockito.when(trackingService.saveProof(
                Mockito.eq(1L),
                Mockito.eq("TRK123"),
                Mockito.eq("John"),
                Mockito.eq("sign"),
                Mockito.eq("ok")
        )).thenReturn(new DeliveryProofResponse());

        mockMvc.perform(post("/tracking/proof")
                        .param("deliveryId", "1")
                        .param("trackingNumber", "TRK123")
                        .param("receiverName", "John")
                        .param("signature", "sign")
                        .param("remarks", "ok"))
                .andExpect(status().isOk());
    }

    // ✅ GET PROOF
    @Test
    void testGetProof() throws Exception {

        Mockito.when(trackingService.getProofByDeliveryId(1L))
                .thenReturn(new DeliveryProofResponse());

        mockMvc.perform(get("/tracking/proof/1"))
                .andExpect(status().isOk());
    }
}