//package com.example.demo.service;
//
//import com.example.demo.client.TrackingClient;
//import com.example.demo.dto.*;
//import com.example.demo.entities.*;
//import com.example.demo.enums.DeliveryStatus;
//import com.example.demo.repository.DeliveryRepository;
//
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.util.*;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//class DeliveryServiceTest {
//
//    @Mock
//    private DeliveryRepository deliveryRepository;
//
//    @Mock
//    private TrackingClient trackingClient;
//
//    @InjectMocks
//    private DeliveryService deliveryService;
//
//    // 🔧 COMMON METHOD (IMPORTANT)
//    private Delivery createMockDelivery() {
//        Delivery d = new Delivery();
//        d.setId(1L);
//        d.setTrackingNumber("TRK123");
//        d.setCustomerEmail("test@example.com");
//        d.setStatus(DeliveryStatus.BOOKED);
//        d.setServiceType("STANDARD");
//        d.setWeight(2.0);
//        d.setDescription("Test");
//
//        Address sender = new Address();
//        sender.setCity("Delhi");
//
//        Address receiver = new Address();
//        receiver.setCity("Mumbai");
//
//        d.setSenderAddress(sender);
//        d.setReceiverAddress(receiver);
//
//        return d;
//    }
//
//    // ✅ CREATE DELIVERY
//    @Test
//    void testCreateDelivery() {
//
//        DeliveryRequest request = new DeliveryRequest();
//
//        AddressRequest sender = new AddressRequest();
//        sender.setFullName("A");
//        sender.setCity("Delhi");
//
//        AddressRequest receiver = new AddressRequest();
//        receiver.setFullName("B");
//        receiver.setCity("Mumbai");
//
//        request.setSenderAddress(sender);
//        request.setReceiverAddress(receiver);
//
//        Delivery saved = createMockDelivery();
//
//        when(deliveryRepository.save(any())).thenReturn(saved);
//
//        DeliveryResponse response = deliveryService.createDelivery(request, "test@example.com");
//
//        assertNotNull(response);
//        assertEquals("test@example.com", response.getCustomerEmail());
//
//        verify(trackingClient).createEvent(any());
//    }
//
//    // ❌ EMAIL MISSING
//    @Test
//    void testCreateDelivery_EmailMissing() {
//        DeliveryRequest request = new DeliveryRequest();
//
//        assertThrows(RuntimeException.class, () ->
//                deliveryService.createDelivery(request, null));
//    }
//
//    // ✅ UPDATE STATUS
//    @Test
//    void testUpdateStatus() {
//
//        Delivery d = createMockDelivery();
//
//        when(deliveryRepository.findById(1L)).thenReturn(Optional.of(d));
//        when(deliveryRepository.save(any())).thenReturn(d);
//
//        DeliveryResponse response = deliveryService.updateStatus(1L, "DELIVERED");
//
//        assertEquals("DELIVERED", response.getStatus());
//        verify(trackingClient).createEvent(any());
//    }
//
//    // ❌ NOT FOUND
//    @Test
//    void testUpdateStatus_NotFound() {
//        when(deliveryRepository.findById(1L)).thenReturn(Optional.empty());
//
//        assertThrows(Exception.class, () ->
//                deliveryService.updateStatus(1L, "DELIVERED"));
//    }
//
//    // ✅ GET ALL
//    @Test
//    void testGetAllDeliveries() {
//
//        when(deliveryRepository.findAll())
//                .thenReturn(List.of(createMockDelivery()));
//
//        List<DeliveryResponse> result = deliveryService.getAllDeliveries();
//
//        assertEquals(1, result.size());
//    }
//
//    // ✅ GET BY ID
//    @Test
//    void testGetById() {
//
//        when(deliveryRepository.findById(1L))
//                .thenReturn(Optional.of(createMockDelivery()));
//
//        DeliveryResponse response = deliveryService.getById(1L);
//
//        assertEquals(1L, response.getId());
//    }
//
//    // ✅ GET BY CUSTOMER
//    @Test
//    void testGetByCustomer() {
//
//        when(deliveryRepository.findByCustomerEmail("test@example.com"))
//                .thenReturn(List.of(createMockDelivery()));
//
//        List<DeliveryResponse> result =
//                deliveryService.getByCustomer("test@example.com");
//
//        assertEquals(1, result.size());
//    }
//}