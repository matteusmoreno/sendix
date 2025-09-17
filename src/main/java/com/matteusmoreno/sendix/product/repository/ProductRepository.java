package com.matteusmoreno.sendix.product.repository;

import com.matteusmoreno.sendix.product.entity.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {
}
