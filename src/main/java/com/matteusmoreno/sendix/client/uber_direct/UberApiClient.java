package com.matteusmoreno.sendix.client.uber_direct;

import com.matteusmoreno.sendix.uber_direct.configuration.UberApiFeignConfiguration;
import com.matteusmoreno.sendix.quote.request.CreateQuoteRequest;
import com.matteusmoreno.sendix.quote.response.UberQuoteDetailsResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "uber-api", url = "https://api.uber.com", configuration = UberApiFeignConfiguration.class)
public interface UberApiClient {

    @PostMapping("/v1/customers/{customerId}/delivery_quotes")
    UberQuoteDetailsResponse getQuote(@PathVariable String customerId, @RequestBody CreateQuoteRequest request);
}