package com.matteusmoreno.sendix.quote.service;

import com.matteusmoreno.sendix.address.entity.Address;
import com.matteusmoreno.sendix.client.uber_direct.UberApiClient;
import com.matteusmoreno.sendix.customer.entity.Customer;
import com.matteusmoreno.sendix.customer.repository.CustomerRepository;
import com.matteusmoreno.sendix.exception.AddressNotFoundException;
import com.matteusmoreno.sendix.exception.CustomerNotFoundException;
import com.matteusmoreno.sendix.exception.StoreNotFoundException;
import com.matteusmoreno.sendix.quote.domain.Quote;
import com.matteusmoreno.sendix.quote.repository.QuoteRepository;
import com.matteusmoreno.sendix.quote.request.CreateQuoteRequest;
import com.matteusmoreno.sendix.quote.response.UberQuoteDetailsResponse;
import com.matteusmoreno.sendix.store.entity.Store;
import com.matteusmoreno.sendix.store.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
public class QuoteService {

    private final QuoteRepository quoteRepository;
    private final UberApiClient uberApiClient;
    private final StoreRepository storeRepository;
    private final CustomerRepository customerRepository;

    public QuoteService(QuoteRepository quoteRepository, UberApiClient uberApiClient, StoreRepository storeRepository, CustomerRepository customerRepository) {
        this.quoteRepository = quoteRepository;
        this.uberApiClient = uberApiClient;
        this.storeRepository = storeRepository;
        this.customerRepository = customerRepository;
    }

    @Value("${uber-direct.api.user-id}")
    private String uberCustomerId;

    public UberQuoteDetailsResponse createQuote(String storeId, String customerId) {
        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new CustomerNotFoundException("Customer not found with ID: " + customerId));
        Store store = storeRepository.findById(storeId).orElseThrow(() -> new StoreNotFoundException("Store not found with ID: " + storeId));
        Address pickupAddressObj = store.getAddresses().stream().findFirst().orElseThrow(() -> new AddressNotFoundException("Store with ID " + storeId + " has no addresses configured."));
        Address dropoffAddressObj = customer.getAddresses().stream().findFirst().orElseThrow(() -> new AddressNotFoundException("Customer with ID " + customerId + " has no addresses configured."));

        String pickupAddress = pickupAddressObj.toUberApiString();
        String dropoffAddress = dropoffAddressObj.toUberApiString();

        CreateQuoteRequest request = CreateQuoteRequest.builder()
                .pickupAddress(pickupAddress)
                .dropoffAddress(dropoffAddress)
                .externalStoreId(store.getStoreId())
                .build();

        UberQuoteDetailsResponse uberResponse = uberApiClient.getQuote(uberCustomerId, request);

        Quote quoteToSave = Quote.builder()
                .quoteId(UUID.randomUUID().toString())
                .uberQuoteId(uberResponse.id())
                .storeId(storeId)
                .customerId(customerId)
                .fee(uberResponse.fee())
                .currencyType(uberResponse.currency_type())
                .duration(uberResponse.duration())
                .createdAt(uberResponse.created())
                .expiresAt(Instant.parse(uberResponse.expires()))
                .build();

        quoteRepository.save(quoteToSave);

        return uberResponse;
    }
}
