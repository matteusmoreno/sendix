package com.matteusmoreno.sendix.customer.repository;

import com.matteusmoreno.sendix.customer.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CustomerRepository extends JpaRepository<Customer, UUID> {
}
