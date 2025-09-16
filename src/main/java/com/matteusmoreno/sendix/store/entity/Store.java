package com.matteusmoreno.sendix.store.entity;


import com.matteusmoreno.sendix.address.entity.Address;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Document(collection = "stores")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter @Setter
public class Store {

    @Id
    private String storeId;
    private String name;
    @Indexed(unique = true)
    private String cnpj;
    @Indexed(unique = true)
    private String email;
    @Indexed(unique = true)
    private String phoneNumber;
    private List<Address> addresses = new ArrayList<>();
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
    private Boolean active;
}