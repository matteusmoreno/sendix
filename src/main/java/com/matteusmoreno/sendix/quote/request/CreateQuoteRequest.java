package com.matteusmoreno.sendix.quote.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreateQuoteRequest {

    @JsonProperty("pickup_address")
    private String pickupAddress;         // [Obrigatório] Endereço de coleta em texto. Ex: "Rua Exemplo, 123, Bairro, Cidade, UF"

    @JsonProperty("dropoff_address")
    private String dropoffAddress;        // [Obrigatório] Endereço de entrega em texto. Ex: "Av. Principal, 456, Centro, Cidade, UF"

    @JsonProperty("pickup_latitude")
    private String pickupLatitude;        // [Alternativa] Latitude do ponto de coleta. Usar em vez de pickupAddress.

    @JsonProperty("pickup_longitude")
    private String pickupLongitude;       // [Alternativa] Longitude do ponto de coleta. Usar em vez de pickupAddress.

    @JsonProperty("dropoff_latitude")
    private String dropoffLatitude;       // [Alternativa] Latitude do ponto de entrega. Usar em vez de dropoffAddress.

    @JsonProperty("dropoff_longitude")
    private String dropoffLongitude;      // [Alternativa] Longitude do ponto de entrega. Usar em vez de dropoffAddress.

    @JsonProperty("pickup_ready_dt")
    private String pickupReadyDt;         // [Opcional] Data/hora (UTC) que o pedido estará pronto para coleta. Para agendamentos.

    @JsonProperty("pickup_deadline_dt")
    private String pickupDeadlineDt;      // [Opcional] Data/hora (UTC) máxima para a coleta do pedido.

    @JsonProperty("dropoff_ready_dt")
    private String dropoffReadyDt;        // [Opcional] Data/hora (UTC) a partir da qual a entrega pode ser feita.

    @JsonProperty("dropoff_deadline_dt")
    private String dropoffDeadlineDt;     // [Opcional] Data/hora (UTC) máxima para a entrega ao cliente.

    @JsonProperty("pickup_phone_number")
    private String pickupPhoneNumber;     // [Opcional] Telefone do local de coleta no formato E.164 (+55...).

    @JsonProperty("dropoff_phone_number")
    private String dropoffPhoneNumber;    // [Opcional] Telefone do destinatário no formato E.164 (+55...).

    @JsonProperty("manifest_total_value")
    private String manifestTotalValue;    // [Opcional] Valor total declarado dos itens em centavos. Ex: "1500" para R$ 15,00.

    @JsonProperty("external_store_id")
    private String externalStoreId;       // [Opcional] ID do seu sistema para associar à entrega. Ex: "PEDIDO-12345".
}