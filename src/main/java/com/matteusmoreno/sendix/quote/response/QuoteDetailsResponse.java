package com.matteusmoreno.sendix.quote.response;

public record QuoteDetailsResponse(
        String kind,               // O tipo de objeto retornado, sempre será "delivery_quote".
        String id,                 // [MUITO IMPORTANTE] O ID único desta cotação (ex: "dqt_..."). Use-o para criar a entrega.
        String created,            // Data/hora (UTC) em que a cotação foi criada.
        String expires,            // Data/hora (UTC) em que esta cotação expira e deixa de ser válida.
        Integer fee,               // O custo da entrega na menor unidade da moeda (centavos). Ex: 900 para R$ 9,00.
        String currency,           // O código da moeda. Ex: "BRL" para Reais.
        String currency_type,      // Tipo da moeda, geralmente repete o valor de 'currency'.
        String dropoff_eta,        // [Estimativa] Data/hora (UTC) prevista para a chegada do entregador ao destino.
        Integer duration,          // [Estimativa] Duração total da entrega em minutos (da coleta à entrega final).
        Integer pickup_duration,   // [Estimativa] Duração em minutos que o entregador levará para chegar ao ponto de coleta.
        String dropoff_deadline    // [Prazo Máximo] Data/hora (UTC) limite garantida para a conclusão da entrega.
) {
}