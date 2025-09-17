package com.matteusmoreno.sendix.product.repository;

import com.matteusmoreno.sendix.product.entity.Product;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {
    boolean existsByProductNameAndManufacturerIgnoreCase(String productName, String manufacturer);

}
