package com.matteusmoreno.sendix.uber_direct.service;

import com.matteusmoreno.sendix.client.uber_direct.UberAuthClient;
import com.matteusmoreno.sendix.uber_direct.request.UberDirectTokenRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class UberDirectService {

    private final UberAuthClient uberAuthClient;

    @Value("${uber-direct.api.client-developer-id}")
    private String clientId;
    @Value("${uber-direct.api.client-secret}")
    private String clientSecret;

    public UberDirectService(UberAuthClient uberAuthClient) {
        this.uberAuthClient = uberAuthClient;
    }

    public String getAccessToken() {
        UberDirectTokenRequest request = UberDirectTokenRequest.builder()
                .clientId(clientId)
                .clientSecret(clientSecret)
                .grantType("client_credentials")
                .scope("eats.deliveries direct.organizations")
                .build();

        return uberAuthClient.getToken(request).accessToken();
    }
}