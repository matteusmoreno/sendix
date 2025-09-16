package com.matteusmoreno.sendix.uber_direct;

import com.matteusmoreno.sendix.client.uber_direct.request.UberDirectTokenRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//CLASS FOR TESTING PURPOSES ONLY
@RestController
@RequestMapping("/uber")
public class UberDirectController {

    private final UberDirectService uberDirectService;

    public UberDirectController(UberDirectService uberDirectService) {
        this.uberDirectService = uberDirectService;
    }

    @PostMapping
    public ResponseEntity<String> getToken() {
        String token = uberDirectService.getAccessToken();
        return ResponseEntity.ok(token);
    }
}
