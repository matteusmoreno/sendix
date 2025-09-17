package com.matteusmoreno.sendix.quote.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Document(collection = "quotes")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter @Setter
public class Quote {

    @Id
    private String quoteId;
    private String uberQuoteId;
    private String storeId;
    private String customerId;
    private Integer fee;
    private String currencyType;
    private Integer duration;
    private String createdAt;
    @Indexed(name = "quote_ttl_index", expireAfter = "0s")
    private Instant expiresAt;
}
