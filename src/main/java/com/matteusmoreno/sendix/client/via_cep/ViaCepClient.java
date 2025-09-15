package com.matteusmoreno.sendix.client.via_cep;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "viacep", url = "https://viacep.com.br/ws/")
public interface ViaCepClient {

    @GetMapping("/{zipcode}/json/")
    ViaCepResponse getAddressByZipcode(@PathVariable String zipcode);
}
