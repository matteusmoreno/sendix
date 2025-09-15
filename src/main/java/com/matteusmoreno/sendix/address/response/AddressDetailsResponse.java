package com.matteusmoreno.sendix.address.response;

import com.matteusmoreno.sendix.address.entity.Address;

import java.util.UUID;

public record AddressDetailsResponse(
        String street,
        String number,
        String complement,
        String neighborhood,
        String city,
        String state,
        String zipCode) {

    public AddressDetailsResponse(Address address) {
        this(
                address.getStreet(),
                address.getNumber(),
                address.getComplement(),
                address.getNeighborhood(),
                address.getCity(),
                address.getState(),
                address.getZipCode());
    }
}
