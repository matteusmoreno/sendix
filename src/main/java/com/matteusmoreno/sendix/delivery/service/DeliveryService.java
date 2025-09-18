package com.matteusmoreno.sendix.delivery.service;

import com.matteusmoreno.sendix.client.uber_direct.UberApiClient;
import com.matteusmoreno.sendix.customer.entity.Customer;
import com.matteusmoreno.sendix.customer.repository.CustomerRepository;
import com.matteusmoreno.sendix.delivery.domain.Delivery;
import com.matteusmoreno.sendix.delivery.repository.DeliveryRepository;
import com.matteusmoreno.sendix.delivery.request.CreateDeliveryFromQuoteRequest;
import com.matteusmoreno.sendix.delivery.request.UberCreateDeliveryRequest;
import com.matteusmoreno.sendix.delivery.response.UberDeliveryDetailsResponse;
import com.matteusmoreno.sendix.exception.CustomerNotFoundException;
import com.matteusmoreno.sendix.exception.StoreNotFoundException;
import com.matteusmoreno.sendix.quote.domain.Quote;
import com.matteusmoreno.sendix.quote.repository.QuoteRepository;
import com.matteusmoreno.sendix.store.entity.Store;
import com.matteusmoreno.sendix.store.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
public class DeliveryService {

    private final DeliveryRepository deliveryRepository;
    private final QuoteRepository quoteRepository;
    private final CustomerRepository customerRepository;
    private final StoreRepository storeRepository;
    private final UberApiClient uberApiClient;

    public DeliveryService(DeliveryRepository deliveryRepository, QuoteRepository quoteRepository, CustomerRepository customerRepository, StoreRepository storeRepository, UberApiClient uberApiClient) {
        this.deliveryRepository = deliveryRepository;
        this.quoteRepository = quoteRepository;
        this.customerRepository = customerRepository;
        this.storeRepository = storeRepository;
        this.uberApiClient = uberApiClient;
    }

    @Value("${uber-direct.api.user-id}")
    private String uberCustomerId;

    public UberDeliveryDetailsResponse createDelivery(CreateDeliveryFromQuoteRequest request) {
        Quote quote = quoteRepository.findById(request.quoteId()).orElseThrow(() -> new RuntimeException("Quote not found with ID: " + request.quoteId()));
        Store store = storeRepository.findById(quote.getStoreId()).orElseThrow(() -> new StoreNotFoundException("Store not found with ID: " + quote.getStoreId()));
        Customer customer = customerRepository.findById(quote.getCustomerId()).orElseThrow(() -> new CustomerNotFoundException("Customer not found with ID: " + quote.getCustomerId()));

        List<UberCreateDeliveryRequest.ManifestItem> manifestItems = List.of(
                new UberCreateDeliveryRequest.ManifestItem("Peças de Moto", 1, "medium", 15000), // R$ 150,00 -> 15000
                new UberCreateDeliveryRequest.ManifestItem("Capacete", 1, "large", 20000),     // R$ 200,00 -> 20000
                new UberCreateDeliveryRequest.ManifestItem("Jaqueta", 1, "large", 15000),      // R$ 150,00 -> 15000
                new UberCreateDeliveryRequest.ManifestItem("Luvas", 1, "small", 5000)         // R$ 50,00 -> 5000
        );

        Integer totalValue = manifestItems.stream()
                .mapToInt(item -> item.price() * item.quantity())
                .sum();

        UberCreateDeliveryRequest uberRequest = new UberCreateDeliveryRequest(
                quote.getUberQuoteId(),
                store.getName(),
                store.getAddresses().stream().findFirst().get().toUberApiString(),
                store.getPhoneNumber(),
                customer.getName(),
                customer.getAddresses().stream().findFirst().get().toUberApiString(),
                customer.getPhoneNumber(),
                manifestItems,
                totalValue,
                "return" // Se não puder entregar, retornar à loja
        );

        UberDeliveryDetailsResponse uberResponse = uberApiClient.createDelivery(uberCustomerId, uberRequest);

        Delivery deliveryToSave = Delivery.builder()
                .deliveryId(UUID.randomUUID().toString())
                .uberDeliveryId(uberResponse.id())
                .quoteId(request.quoteId())
                .status(uberResponse.status())
                .trackingUrl(uberResponse.trackingUrl())
                .fee(uberResponse.fee())
                .currency(uberResponse.currency())
                .createdAt(Instant.parse(uberResponse.created()))
                .build();

        deliveryRepository.save(deliveryToSave);

        return uberResponse;
    }
}
