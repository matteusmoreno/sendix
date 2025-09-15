package com.matteusmoreno.sendix.address.entity;

import com.matteusmoreno.sendix.customer.entity.Customer;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "addresses")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter @Setter
public class Address {

    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private UUID addressId;
    private String street;
    private String number;
    private String complement;
    private String neighborhood;
    private String city;
    private String state;
    private String zipCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;
}
