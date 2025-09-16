package com.matteusmoreno.sendix.client.uber_direct.request;

import feign.form.FormProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
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
