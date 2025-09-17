package com.matteusmoreno.sendix.product.controller;

import com.matteusmoreno.sendix.product.entity.Product;
import com.matteusmoreno.sendix.product.request.CreateProductRequest;
import com.matteusmoreno.sendix.product.request.UpdateProductRequest;
import com.matteusmoreno.sendix.product.response.ProductDetailsResponse;
import com.matteusmoreno.sendix.product.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/create")
    public ResponseEntity<ProductDetailsResponse> create(@RequestBody @Valid CreateProductRequest request, UriComponentsBuilder uriBuilder) {
        Product product = productService.createProduct(request);
        URI uri = uriBuilder.path("/products/create/{id}").buildAndExpand(product.getProductId()).toUri();

        return ResponseEntity.created(uri).body(new ProductDetailsResponse(product));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDetailsResponse> getById(@PathVariable("id") String productId) {
        Product product = productService.getProductById(productId);

        return ResponseEntity.ok(new ProductDetailsResponse(product));
    }

    @PutMapping("/update")
    public ResponseEntity<ProductDetailsResponse> update(@RequestBody @Valid UpdateProductRequest request) {
        Product product = productService.updateProduct(request);

        return ResponseEntity.ok(new ProductDetailsResponse(product));
    }

    @DeleteMapping("/disable/{id}")
    public ResponseEntity<Void> disable(@PathVariable("id") String productId) {
        productService.disableProductById(productId);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/enable/{id}")
    public ResponseEntity<Void> enable(@PathVariable("id") String productId) {
        productService.enableProductById(productId);
        return ResponseEntity.noContent().build();
    }
}
