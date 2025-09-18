package com.matteusmoreno.sendix.delivery.request;

import jakarta.validation.constraints.NotBlank;

public record CreateDeliveryFromQuoteRequest(
        @NotBlank(message = "Quote ID is required")
        String quoteId
) {}
