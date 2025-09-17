package com.matteusmoreno.sendix.store.service;

import com.matteusmoreno.sendix.address.entity.Address;
import com.matteusmoreno.sendix.address.service.AddressService;
import com.matteusmoreno.sendix.store.entity.Store;
import com.matteusmoreno.sendix.store.repository.StoreRepository;
import com.matteusmoreno.sendix.store.request.CreateStoreRequest;
import com.matteusmoreno.sendix.utils.Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class StoreService {

    private final StoreRepository storeRepository;
    private final AddressService addressService;
    private final Utils utils;

    public StoreService(StoreRepository storeRepository, AddressService addressService, Utils utils) {
        this.storeRepository = storeRepository;
        this.addressService = addressService;
        this.utils = utils;
    }

    @Transactional
    public Store createStore(CreateStoreRequest request) {
        Address address = addressService.createAddressObject(request.zipCode(), request.addressNumber(), request.complement());
        log.info("Address created: {}", address);

        Store store = Store.builder()
                .storeId(UUID.randomUUID().toString())
                .name(request.name())
                .cnpj(request.cnpj())
                .email(request.email())
                .phoneNumber(utils.formatPhoneNumber((request.phoneNumber())))
                .addresses(List.of(address))
                .createdAt(LocalDateTime.now())
                .updatedAt(null)
                .deletedAt(null)
                .active(true)
                .build();

        log.info("Store created: {}", store);

        return storeRepository.save(store);
    }
}
