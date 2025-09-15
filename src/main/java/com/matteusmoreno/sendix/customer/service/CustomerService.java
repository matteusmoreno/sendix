package com.matteusmoreno.sendix.customer.service;

import com.matteusmoreno.sendix.address.entity.Address;
import com.matteusmoreno.sendix.address.service.AddressService;
import com.matteusmoreno.sendix.client.via_cep.ViaCepClient;
import com.matteusmoreno.sendix.client.via_cep.ViaCepResponse;
import com.matteusmoreno.sendix.customer.entity.Customer;
import com.matteusmoreno.sendix.customer.repository.CustomerRepository;
import com.matteusmoreno.sendix.customer.request.CreateCustomerRequest;
import com.matteusmoreno.sendix.utils.Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final AddressService addressService;
    private final Utils utils;

    public CustomerService(CustomerRepository customerRepository, AddressService addressService, Utils utils) {
        this.customerRepository = customerRepository;
        this.addressService = addressService;
        this.utils = utils;
    }

    public Customer createCustomer(CreateCustomerRequest request) {
        Address address = addressService.createAddressObject(request.zipCode(), request.addressNumber(), request.complement());
        log.info("Address created: {}", address);

        Customer customer = Customer.builder()
                .customerId(UUID.randomUUID().toString())
                .name(request.name())
                .email(request.email())
                .phoneNumber(utils.formatPhoneNumber(request.phoneNumber()))
                .birthDate(request.birthDate())
                .addresses(List.of(address))
                .createdAt(LocalDateTime.now())
                .updatedAt(null)
                .deletedAt(null)
                .active(true)
                .build();

        log.info("Customer created: {}", customer);

        return customerRepository.save(customer);
    }
}
