package com.matteusmoreno.sendix.uber_direct;

import com.matteusmoreno.sendix.client.uber_direct.UberDirectApi;
import com.matteusmoreno.sendix.client.uber_direct.request.UberDirectTokenRequest;
import com.matteusmoreno.sendix.client.uber_direct.response.UberDirectTokenResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class UberDirectService {

    private final UberDirectApi uberDirectApi;

    @Value("${uber-direct.api.client-developer-id}")
    private String clientId;
    @Value("${uber-direct.api.client-secret}")
    private String clientSecret;

    public UberDirectService(UberDirectApi uberDirectApi) {
        this.uberDirectApi = uberDirectApi;
    }

    public String getAccessToken() {
        UberDirectTokenRequest request = UberDirectTokenRequest.builder()
                .clientId(clientId)
                .clientSecret(clientSecret)
                .grantType("client_credentials")
                .scope("eats.deliveries direct.organizations")
                .build();

        return uberDirectApi.getToken(request).accessToken();
    }
}
