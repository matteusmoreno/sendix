package com.matteusmoreno.sendix.quote.service;

import com.matteusmoreno.sendix.address.entity.Address;
import com.matteusmoreno.sendix.client.uber_direct.UberApiClient;
import com.matteusmoreno.sendix.customer.entity.Customer;
import com.matteusmoreno.sendix.customer.repository.CustomerRepository;
import com.matteusmoreno.sendix.quote.request.CreateQuoteRequest;
import com.matteusmoreno.sendix.quote.response.QuoteDetailsResponse;
import com.matteusmoreno.sendix.store.entity.Store;
import com.matteusmoreno.sendix.store.repository.StoreRepository;
import com.matteusmoreno.sendix.uber_direct.UberDirectService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class QuoteService {

    private final UberDirectService uberDirectService;
    private final UberApiClient uberApiClient;
    private final StoreRepository storeRepository;
    private final CustomerRepository customerRepository;

    public QuoteService(UberDirectService uberDirectService, UberApiClient uberApiClient, StoreRepository storeRepository, CustomerRepository customerRepository) {
        this.uberDirectService = uberDirectService;
        this.uberApiClient = uberApiClient;
        this.storeRepository = storeRepository;
        this.customerRepository = customerRepository;
    }

    @Value("${uber-direct.api.user-id}")
    private String uberCustomerId;

    public QuoteDetailsResponse createQuote(String storeId, String customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new IllegalArgumentException("Customer not found with ID: " + customerId));

        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new IllegalArgumentException("Store not found with ID: " + storeId));

        Address pickupAddressObj = store.getAddresses().stream().findFirst()
                .orElseThrow(() -> new IllegalStateException("Store with ID " + storeId + " has no addresses configured."));

        Address dropoffAddressObj = customer.getAddresses().stream().findFirst()
                .orElseThrow(() -> new IllegalStateException("Customer with ID " + customerId + " has no addresses configured."));

        String pickupAddress = pickupAddressObj.toUberApiString();
        String dropoffAddress = dropoffAddressObj.toUberApiString();

        CreateQuoteRequest request = CreateQuoteRequest.builder()
                .pickupAddress(pickupAddress)
                .dropoffAddress(dropoffAddress)
                .externalStoreId(store.getStoreId())
                .build();

        return uberApiClient.getQuote(uberCustomerId, request);
    }
}
