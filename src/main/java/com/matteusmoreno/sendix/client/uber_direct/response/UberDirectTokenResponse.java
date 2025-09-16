package com.matteusmoreno.sendix.client.uber_direct.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record UberDirectTokenResponse(
        @JsonProperty("access_token")
        String accessToken,
        @JsonProperty("token_type")
        String tokenType,
        @JsonProperty("expires_in")
        Integer expiresIn,
        String scope
) {
}