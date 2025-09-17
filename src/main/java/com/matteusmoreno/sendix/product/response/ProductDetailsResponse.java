package com.matteusmoreno.sendix.product.response;

import com.matteusmoreno.sendix.product.entity.Product;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ProductDetailsResponse(
        String productId,
        String productName,
        String description,
        String manufacturer,
        String productCategory,
        BigDecimal price,
        Integer stockQuantity,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        LocalDateTime deletedAt,
        Boolean active) {

    public ProductDetailsResponse(Product product) {
        this(
                product.getProductId(),
                product.getProductName(),
                product.getDescription(),
                product.getManufacturer(),
                product.getProductCategory(),
                product.getPrice(),
                product.getStockQuantity(),
                product.getCreatedAt(),
                product.getUpdatedAt(),
                product.getDeletedAt(),
                product.getActive()
        );
    }
}
