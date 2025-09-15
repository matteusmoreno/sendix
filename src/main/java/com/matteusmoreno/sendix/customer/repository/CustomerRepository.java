package com.matteusmoreno.sendix.customer.repository;

import com.matteusmoreno.sendix.customer.entity.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerRepository extends MongoRepository<Customer, String> {
}
