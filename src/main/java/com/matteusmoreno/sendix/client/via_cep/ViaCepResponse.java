package com.matteusmoreno.sendix.client.via_cep;

public record ViaCepResponse(
        String cep,
        String logradouro,
        String bairro,
        String localidade,
        String uf) {
}
