package com.matteusmoreno.sendix.delivery.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public record UberDeliveryDetailsResponse(

        // --- Identificação e Rastreamento ---
        String id,                                  // ID único da entrega gerado pela Uber ("del_...").
        String status,                              // Status atual da entrega (pending, pickup, delivered, etc.).
        @JsonProperty("tracking_url")
        String trackingUrl,                         // URL de rastreamento em tempo real para o cliente.
        @JsonProperty("quote_id")
        String quoteId,                             // ID da cotação original que gerou esta entrega.

        // --- Custos ---
        Integer fee,                                // Custo final da entrega em centavos.
        Integer tip,                                // Gorjeta para o entregador em centavos.
        String currency,                            // Código da moeda (ex: "BRL").

        // --- Timestamps e Estimativas ---
        String created,                             // Data/hora (UTC) de criação da entrega.
        @JsonProperty("dropoff_eta")
        String dropoffEta,                          // Estimativa de chegada no cliente (UTC).
        @JsonProperty("dropoff_deadline")
        String dropoffDeadline,                     // Prazo final para a entrega (UTC).
        @JsonProperty("pickup_eta")
        String pickupEta,                           // Estimativa de chegada na loja para coleta (UTC).

        // --- Pessoas e Locais ---
        Courier courier,                            // Detalhes do entregador (disponível após o aceite).
        Waypoint pickup,                            // Detalhes do local de coleta (sua loja).
        Waypoint dropoff,                           // Detalhes do local de entrega (cliente).

        // --- Pacote ---
        Manifest manifest,                          // Informações gerais sobre o que está sendo entregue.
        @JsonProperty("manifest_items")
        List<ManifestItem> manifestItems,           // Lista detalhada dos itens da entrega.
        @JsonProperty("external_id")
        String externalId                           // ID externo do seu sistema (ex: número do pedido).

) {

    /** Detalhes do entregador. */
    public record Courier(
            String name,
            String phone_number,
            String vehicle_type,
            Float rating,
            @JsonProperty("img_href")
            String pictureUrl
    ) {}

    /** Representa um ponto de parada (coleta ou entrega). */
    public record Waypoint(
            String name,
            @JsonProperty("phone_number")
            String phoneNumber,
            String address,
            String notes
    ) {}

    /** Informações sobre o conteúdo da entrega. */
    public record Manifest(
            String reference,
            String description,
            @JsonProperty("total_value")
            Integer totalValue
    ) {}

    /** Um item específico da entrega. */
    public record ManifestItem(
            String name,
            Integer quantity,
            String size,
            Integer price
    ) {}
}