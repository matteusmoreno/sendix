package com.matteusmoreno.sendix.store;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record CreateStoreRequest(
        @NotBlank(message = "Store Name is required")
        String name,
        @NotBlank(message = "CNPJ is required")
        @Pattern(regexp = "\\d{2}\\.\\d{3}\\.\\d{3}/\\d{4}-\\d{2}", message = "CNPJ must be in the format XX.XXX.XXX/XXXX-XX")
        String cnpj,
        @Email(message = "Invalid email format")
        String email,
        @NotBlank(message = "Phone number is required")
        @Pattern(regexp = "\\(\\d{2}\\)\\d{4,5}-\\d{4}", message = "Phone addressNumber must be in the format (xx)xxxxx-xxxx")
        String phoneNumber,
        @Pattern(regexp = "\\d{5}-\\d{3}", message = "Zip code must be in the format xxxxx-xxx")
        String zipCode,
        String addressNumber,
        String complement
) {
}
