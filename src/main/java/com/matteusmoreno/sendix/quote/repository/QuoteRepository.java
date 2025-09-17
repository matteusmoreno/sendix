package com.matteusmoreno.sendix.quote.repository;

import com.matteusmoreno.sendix.quote.domain.Quote;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface QuoteRepository extends MongoRepository<Quote, String> {
}
