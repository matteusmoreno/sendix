package com.matteusmoreno.sendix.client.uber_direct.request;

import feign.form.FormProperty;
import lombok.Builder;

@Builder
public class UberDirectTokenRequest {
    @FormProperty("client_id")
    private String clientId;

    @FormProperty("client_secret")
    private String clientSecret;

    @FormProperty("grant_type")
    private String grantType;

    @FormProperty("scope")
    private String scope;
}
