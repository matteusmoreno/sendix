package com.matteusmoreno.sendix.product.service;

import com.matteusmoreno.sendix.exception.ProductNotFoundException;
import com.matteusmoreno.sendix.product.entity.Product;
import com.matteusmoreno.sendix.product.repository.ProductRepository;
import com.matteusmoreno.sendix.product.request.CreateProductRequest;
import com.matteusmoreno.sendix.product.request.UpdateProductRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional
    public Product createProduct(CreateProductRequest request) {
        Product product = Product.builder()
                .productId(UUID.randomUUID().toString())
                .productName(request.productName())
                .description(request.description())
                .manufacturer(request.manufacturer())
                .price(request.price())
                .stockQuantity(request.stockQuantity())
                .createdAt(LocalDateTime.now())
                .updatedAt(null)
                .deletedAt(null)
                .active(true)
                .build();

        log.info("Product created: {}", product);
        return productRepository.save(product);
    }

    public Product getProductById(String productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with ID: " + productId));
    }

    @Transactional
    public Product updateProduct(UpdateProductRequest request) {
        Product product = getProductById(request.productId());
        log.info("Updating product: {}", product);

        if (request.productName() != null) product.setProductName(request.productName());
        if (request.description() != null) product.setDescription(request.description());
        if (request.manufacturer() != null) product.setManufacturer(request.manufacturer());
        if (request.price() != null) product.setPrice(request.price());

        product.setUpdatedAt(LocalDateTime.now());
        log.info("Product updated: {}", product);

        return productRepository.save(product);
    }
}
