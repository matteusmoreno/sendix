package com.matteusmoreno.sendix.customer.controller;

import com.matteusmoreno.sendix.customer.request.CreateCustomerRequest;
import com.matteusmoreno.sendix.customer.entity.Customer;
import com.matteusmoreno.sendix.customer.response.CustomerDetailsResponse;
import com.matteusmoreno.sendix.customer.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/create")
    public ResponseEntity<CustomerDetailsResponse> create(@RequestBody @Valid CreateCustomerRequest request, UriComponentsBuilder uriBuilder) {
        Customer customer = customerService.createCustomer(request);
        URI uri = uriBuilder.path("/customers/create/{id}").buildAndExpand(customer.getCustomerId()).toUri();
        return ResponseEntity.created(uri).body(new CustomerDetailsResponse(customer));
    }
}
