package com.matteusmoreno.sendix.quote.controller;

import com.matteusmoreno.sendix.quote.request.StoreIdAndCustomerIdRequest;
import com.matteusmoreno.sendix.quote.response.QuoteDetailsResponse;
import com.matteusmoreno.sendix.quote.service.QuoteService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/quotes")
public class QuoteController {

    private final QuoteService quoteService;

    public QuoteController(QuoteService quoteService) {
        this.quoteService = quoteService;
    }

    @PostMapping
    public ResponseEntity<QuoteDetailsResponse> createQuote(@RequestBody @Valid StoreIdAndCustomerIdRequest request) {
        QuoteDetailsResponse quote = quoteService.createQuote(request.storeId(), request.customerId());

        return ResponseEntity.ok(quote);
    }
}
