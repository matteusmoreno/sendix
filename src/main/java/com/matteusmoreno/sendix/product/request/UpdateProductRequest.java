package com.matteusmoreno.sendix.product.request;

import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;

public record UpdateProductRequest(
        @NotBlank(message = "Product ID is required")
        String productId,
        String productName,
        String description,
        String manufacturer,
        BigDecimal price) {
}
