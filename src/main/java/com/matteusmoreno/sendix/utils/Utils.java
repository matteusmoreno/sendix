package com.matteusmoreno.sendix.utils;

import org.springframework.stereotype.Component;

@Component
public class Utils {

    public String formatPhoneNumber(String phoneNumber) {
        return "+55" + phoneNumber.replaceAll("[()\\-]", "");
    }
}
