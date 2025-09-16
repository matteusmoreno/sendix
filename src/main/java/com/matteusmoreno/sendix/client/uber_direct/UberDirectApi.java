package com.matteusmoreno.sendix.client.uber_direct;

import com.matteusmoreno.sendix.client.uber_direct.configuration.UberAuthFeignConfiguration;
import com.matteusmoreno.sendix.client.uber_direct.request.UberDirectTokenRequest;
import com.matteusmoreno.sendix.client.uber_direct.response.UberDirectTokenResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "uber-auth", url = "https://login.uber.com", configuration = UberAuthFeignConfiguration.class)
public interface UberDirectApi {

    // Definimos o Content-Type que a API espera
    @PostMapping(value = "/oauth/v2/token", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    UberDirectTokenResponse getToken(@RequestBody UberDirectTokenRequest request);
}
