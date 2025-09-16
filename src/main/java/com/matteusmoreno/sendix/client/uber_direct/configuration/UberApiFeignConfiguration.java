package com.matteusmoreno.sendix.client.uber_direct.configuration;

import com.matteusmoreno.sendix.uber_direct.UberDirectService;
import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;

public class UberApiFeignConfiguration {

    private final UberDirectService uberDirectService;

    public UberApiFeignConfiguration(UberDirectService uberDirectService) {
        this.uberDirectService = uberDirectService;
    }

    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
            String accessToken = uberDirectService.getAccessToken();
            requestTemplate.header("Authorization", "Bearer " + accessToken);
        };
    }
}