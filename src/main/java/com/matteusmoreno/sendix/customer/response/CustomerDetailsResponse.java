package com.matteusmoreno.sendix.customer.response;

import com.matteusmoreno.sendix.address.response.AddressDetailsResponse;
import com.matteusmoreno.sendix.customer.entity.Customer;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record CustomerDetailsResponse(
        UUID customerId,
        String name,
        String email,
        String phoneNumber,
        LocalDate birthDate,
        Integer age,
        List<AddressDetailsResponse> addresses,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        LocalDateTime deletedAt,
        Boolean active) {

        public CustomerDetailsResponse(Customer customer) {
            this(
                    customer.getCustomerId(),
                    customer.getName(),
                    customer.getEmail(),
                    customer.getPhoneNumber(),
                    customer.getBirthDate(),
                    customer.getAge(),
                    customer.getAddresses().stream().map(AddressDetailsResponse::new).toList(),
                    customer.getCreatedAt(),
                    customer.getUpdatedAt(),
                    customer.getDeletedAt(),
                    customer.getActive());
        }
}
