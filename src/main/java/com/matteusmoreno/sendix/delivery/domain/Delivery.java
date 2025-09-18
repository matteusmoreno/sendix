package com.matteusmoreno.sendix.delivery.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Document(collection = "deliveries")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter @Setter
public class Delivery {

    @Id
    private String deliveryId;
    private String uberDeliveryId;
    private String quoteId;
    private String status;
    private String trackingUrl;
    private Integer fee;
    private String currency;
    private Instant createdAt;
}
