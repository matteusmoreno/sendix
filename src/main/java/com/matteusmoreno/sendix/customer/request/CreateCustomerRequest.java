package com.matteusmoreno.sendix.customer.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;

public record CreateCustomerRequest(
        @NotBlank(message = "Name is required")
        String name,
        @NotBlank(message = "Email is required")
        @Email(message = "Email must be valid")
        String email,
        @NotBlank(message = "Phone addressNumber is required")
        @Pattern(regexp = "\\(\\d{2}\\)\\d{4,5}-\\d{4}", message = "Phone addressNumber must be in the format (xx)xxxxx-xxxx")
        String phoneNumber,
        @NotNull(message = "Birth date is required")
        @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "Birth date must be in the format yyyy-MM-dd")
        LocalDate birthDate,
        @Pattern(regexp = "\\d{5}-\\d{3}", message = "Zip code must be in the format xxxxx-xxx")
        String zipCode,
        String addressNumber,
        String complement) {
}
