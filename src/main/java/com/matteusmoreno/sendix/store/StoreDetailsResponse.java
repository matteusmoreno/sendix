package com.matteusmoreno.sendix.store;

import com.matteusmoreno.sendix.address.response.AddressDetailsResponse;

import java.time.LocalDateTime;
import java.util.List;

public record StoreDetailsResponse(
        String storeId,
        String name,
        String cnpj,
        String email,
        String phoneNumber,
        List<AddressDetailsResponse> addresses,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        LocalDateTime deletedAt,
        Boolean active) {

    public StoreDetailsResponse(Store store) {
        this(
                store.getStoreId(),
                store.getName(),
                store.getCnpj(),
                store.getEmail(),
                store.getPhoneNumber(),
                store.getAddresses().stream().map(AddressDetailsResponse::new).toList(),
                store.getCreatedAt(),
                store.getUpdatedAt(),
                store.getDeletedAt(),
                store.getActive()
        );
    }
}
