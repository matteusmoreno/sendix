package com.matteusmoreno.sendix.store.repository;

import com.matteusmoreno.sendix.store.entity.Store;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StoreRepository extends MongoRepository<Store, String> {
}
