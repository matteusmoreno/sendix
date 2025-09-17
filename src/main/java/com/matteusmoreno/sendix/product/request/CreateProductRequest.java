package com.matteusmoreno.sendix.product.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record CreateProductRequest(
        @NotBlank(message = "Product name is required")
        String productName,
        String description,
        @NotBlank(message = "Product category is required")
        String productCategory,
        @NotBlank(message = "Manufacturer is required")
        String manufacturer,
        @NotNull(message = "Price is required")
        BigDecimal price,
        @NotNull(message = "Stock quantity is required")
        Integer stockQuantity,
        String imageUrl) {
}
