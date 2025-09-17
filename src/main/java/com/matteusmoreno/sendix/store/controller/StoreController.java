package com.matteusmoreno.sendix.store.controller;

import com.matteusmoreno.sendix.store.entity.Store;
import com.matteusmoreno.sendix.store.service.StoreService;
import com.matteusmoreno.sendix.store.request.CreateStoreRequest;
import com.matteusmoreno.sendix.store.response.StoreDetailsResponse;
import jakarta.validation.Valid;
import lombok.Getter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/stores")
public class StoreController {

    private final StoreService storeService;

    public StoreController(StoreService storeService) {
        this.storeService = storeService;
    }

    @PostMapping("/create")
    public ResponseEntity<StoreDetailsResponse> create(@RequestBody @Valid CreateStoreRequest request, UriComponentsBuilder uriBuilder) {
        Store store = storeService.createStore(request);
        URI uri = uriBuilder.path("/stores/create/{id}").buildAndExpand(store.getStoreId()).toUri();

        return ResponseEntity.created(uri).body(new StoreDetailsResponse(store));
    }

    @GetMapping("/{id}")
    public ResponseEntity<StoreDetailsResponse> getStoreById(@PathVariable("id") String storeId) {
        Store store = storeService.getStoreById(storeId);

        return ResponseEntity.ok(new StoreDetailsResponse(store));
    }
}
