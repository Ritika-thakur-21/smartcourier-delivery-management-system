//package com.example.demo.controller;
//
//import com.example.demo.dto.DeliveryRequest;
//import com.example.demo.dto.DeliveryResponse;
//import com.example.demo.service.DeliveryService;
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//
//import java.util.List;
//
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//@ExtendWith(MockitoExtension.class)
//class DeliveryControllerTest {
//
//    private MockMvc mockMvc;
//
//    @Mock
//    private DeliveryService deliveryService;
//
//    @InjectMocks
//    private DeliveryController deliveryController;
//
//    private ObjectMapper objectMapper = new ObjectMapper();
//
//    @BeforeEach
//    void setup() {
//        mockMvc = MockMvcBuilders
//                .standaloneSetup(deliveryController)
//                .build();
//    }
//
//    // CREATE DELIVERY
//    @Test
//    void testCreateDelivery() throws Exception {
//
//        DeliveryRequest request = new DeliveryRequest();
//        DeliveryResponse response = new DeliveryResponse();
//
//        Mockito.when(deliveryService.createDelivery(Mockito.any(), Mockito.any()))
//                .thenReturn(response);
//
//        mockMvc.perform(post("/deliveries")
//                        .header("X-User-Email", "test@example.com")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(request)))
//                .andExpect(status().isOk());
//    }
//
//    //  HEADER MISSING
////    @Test
////    void testCreateDelivery_HeaderMissing() throws Exception {
////
////        DeliveryRequest request = new DeliveryRequest();
////
////        mockMvc.perform(post("/deliveries")
////                        .contentType(MediaType.APPLICATION_JSON)
////                        .content(objectMapper.writeValueAsString(request)))
////                .andExpect(status().isInternalServerError());
////    }
//
//    //  GET MY DELIVERIES
//    @Test
//    void testMyDeliveries() throws Exception {
//
//        Mockito.when(deliveryService.getByCustomer(Mockito.any()))
//                .thenReturn(List.of(new DeliveryResponse()));
//
//        mockMvc.perform(get("/deliveries/my")
//                        .header("X-User-Email", "test@example.com"))
//                .andExpect(status().isOk());
//    }
//
//    // GET ALL
//    @Test
//    void testGetAll() throws Exception {
//
//        Mockito.when(deliveryService.getAllDeliveries())
//                .thenReturn(List.of(new DeliveryResponse()));
//
//        mockMvc.perform(get("/deliveries"))
//                .andExpect(status().isOk());
//    }
//
//    //  GET BY ID
//    @Test
//    void testGetById() throws Exception {
//
//        Mockito.when(deliveryService.getById(1L))
//                .thenReturn(new DeliveryResponse());
//
//        mockMvc.perform(get("/deliveries/1"))
//                .andExpect(status().isOk());
//    }
//
//    //  UPDATE STATUS
//    @Test
//    void testUpdateStatus() throws Exception {
//
//        Mockito.when(deliveryService.updateStatus(Mockito.eq(1L), Mockito.any()))
//                .thenReturn(new DeliveryResponse());
//
//        mockMvc.perform(put("/deliveries/1/status")
//                        .param("status", "DELIVERED"))
//                .andExpect(status().isOk());
//    }
//}