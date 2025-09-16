package com.matteusmoreno.sendix.address.entity;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter @Setter
public class Address {

    private String street;
    private String number;
    private String complement;
    private String neighborhood;
    private String city;
    private String state;
    private String zipCode;

    public String toUberApiString() {
        StringBuilder formattedAddress = new StringBuilder();

        formattedAddress.append(street).append(", ").append(number);

        if (complement != null && !complement.isBlank()) {
            formattedAddress.append(", ").append(complement);
        }

        formattedAddress.append(", ").append(neighborhood);
        formattedAddress.append(" - ").append(city);
        formattedAddress.append(", ").append(state);

        return formattedAddress.toString();
    }
}
