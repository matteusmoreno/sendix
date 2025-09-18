package com.matteusmoreno.sendix.delivery.controller;

import com.matteusmoreno.sendix.delivery.service.DeliveryService;
import com.matteusmoreno.sendix.delivery.request.CreateDeliveryFromQuoteRequest;
import com.matteusmoreno.sendix.delivery.response.UberDeliveryDetailsResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/deliveries")
public class DeliveryController {
    private final DeliveryService deliveryService;

    public DeliveryController(DeliveryService deliveryService) {
        this.deliveryService = deliveryService;
    }

    @PostMapping
    public ResponseEntity<UberDeliveryDetailsResponse> createDelivery(@RequestBody @Valid CreateDeliveryFromQuoteRequest request) {
        UberDeliveryDetailsResponse delivery = deliveryService.createDelivery(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(delivery);
    }
}
