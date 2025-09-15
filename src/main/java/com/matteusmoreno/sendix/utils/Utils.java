package com.matteusmoreno.sendix.utils;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Period;

@Component
public class Utils {

    public Integer calculateAge(LocalDate birthDate) {
        return Period.between(birthDate, LocalDate.now()).getYears();
    }

    public String formatPhoneNumber(String phoneNumber) {
        return "+55" + phoneNumber.replaceAll("[()\\-]", "");
    }
}
