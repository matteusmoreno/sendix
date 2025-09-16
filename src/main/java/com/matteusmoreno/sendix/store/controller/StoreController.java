package com.matteusmoreno.sendix.store.controller;

import com.matteusmoreno.sendix.store.entity.Store;
import com.matteusmoreno.sendix.store.service.StoreService;
import com.matteusmoreno.sendix.store.request.CreateStoreRequest;
import com.matteusmoreno.sendix.store.response.StoreDetailsResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
}
