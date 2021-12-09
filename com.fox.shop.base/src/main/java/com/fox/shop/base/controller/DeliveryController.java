package com.fox.shop.base.controller;

import com.fox.shop.base.service.i.DeliveryService;
import com.fox.shop.protocol.DeliveryModel;
import com.fox.shop.protocol.request.DeliveryOnCreateRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/delivery")
public class DeliveryController {

    private final DeliveryService deliveryService;

    public DeliveryController(
            final DeliveryService deliveryService
    ) {
        this.deliveryService = deliveryService;
    }

    @PostMapping
    public ResponseEntity<DeliveryModel> save(
            @RequestBody final DeliveryOnCreateRequest request
    ) {
        return ResponseEntity.ok(deliveryService.save(request));
    }

    @GetMapping("all")
    public ResponseEntity<List<DeliveryModel>> save() {
        return ResponseEntity.ok(deliveryService.getAll());
    }
}











