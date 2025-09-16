package com.matteusmoreno.sendix.quote.request;

import jakarta.validation.constraints.NotBlank;

public record StoreIdAndCustomerIdRequest(
        @NotBlank(message = "Store ID is required")
        String storeId,
        @NotBlank(message = "Customer ID is required")
        String customerId) {
}
