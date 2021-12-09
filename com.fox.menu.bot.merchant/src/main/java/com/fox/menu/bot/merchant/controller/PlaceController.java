package com.fox.menu.bot.merchant.controller;

import com.fox.menu.billing.protocol.response.GeneralResponse;
import com.fox.menu.bot.merchant.ui.scenarios.i.PlaceScenarios;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/place")
public class PlaceController {

    private final PlaceScenarios placeScenarios;

    public PlaceController(
            final PlaceScenarios placeScenarios
    ) {
        this.placeScenarios = placeScenarios;
    }

    @PostMapping("check-of-available")
    public ResponseEntity<GeneralResponse> checkOfAvailable(
            @RequestParam final long customerId,
            @RequestParam final long waiterId,
            @RequestParam final List<Long> placeIds
    ) {
        placeScenarios.checkOfAvailableTitle(customerId, waiterId, placeIds);
        return ResponseEntity.ok(GeneralResponse.ok());
    }
}

















