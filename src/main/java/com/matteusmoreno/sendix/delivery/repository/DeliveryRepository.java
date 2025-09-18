package com.matteusmoreno.sendix.delivery.repository;

import com.matteusmoreno.sendix.delivery.domain.Delivery;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DeliveryRepository extends MongoRepository<Delivery, String> {
}
