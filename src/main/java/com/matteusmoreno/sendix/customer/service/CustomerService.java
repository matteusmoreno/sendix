package com.matteusmoreno.sendix.customer.service;

import com.matteusmoreno.sendix.address.entity.Address;
import com.matteusmoreno.sendix.address.service.AddressService;
import com.matteusmoreno.sendix.customer.request.CreateCustomerRequest;
import com.matteusmoreno.sendix.customer.entity.Customer;
import com.matteusmoreno.sendix.customer.repository.CustomerRepository;
import com.matteusmoreno.sendix.utils.Utils;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class CustomerService {

    private final AddressService addressService;
    private final CustomerRepository customerRepository;
    private final Utils utils;

    public CustomerService(AddressService addressService, CustomerRepository customerRepository, Utils utils) {
        this.addressService = addressService;
        this.customerRepository = customerRepository;
        this.utils = utils;
    }

    @Transactional
    public Customer createCustomer(CreateCustomerRequest request) {
        Address address = addressService.createAddress(request.zipCode(), request.addressNumber(), request.complement());
        log.info("Address created: {}", address);

        Customer customer = Customer.builder()
                .name(request.name())
                .email(request.email())
                .phoneNumber(utils.formatPhoneNumber(request.phoneNumber()))
                .birthDate(request.birthDate())
                .age(utils.calculateAge(request.birthDate()))
                .createdAt(LocalDateTime.now())
                .updatedAt(null)
                .deletedAt(null)
                .active(true)
                .build();

        customer.setAddresses(List.of(address));
        address.setCustomer(customer);

        log.info("Customer created: {}", customer);
        return customerRepository.save(customer);
    }
}
