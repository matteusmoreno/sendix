package com.matteusmoreno.sendix.delivery.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record UberCreateDeliveryRequest(
        @JsonProperty("quote_id")
        String quoteId,

        @JsonProperty("pickup_name")
        String pickupName,

        @JsonProperty("pickup_address")
        String pickupAddress,

        @JsonProperty("pickup_phone_number")
        String pickupPhoneNumber,

        @JsonProperty("dropoff_name")
        String dropoffName,

        @JsonProperty("dropoff_address")
        String dropoffAddress,

        @JsonProperty("dropoff_phone_number")
        String dropoffPhoneNumber,

        @JsonProperty("manifest_items")
        List<ManifestItem> manifestItems,

        @JsonProperty("manifest_total_value")
        Integer manifestTotalValue,

        @JsonProperty("undeliverable_action")
        String undeliverableAction
) {
    public record ManifestItem(
            String name,
            Integer quantity,
            String size,
            Integer price
    ) {}
}
