package com.fox.shop.base.controller;

import com.fox.shop.base.service.i.MerchantService;
import com.fox.shop.protocol.MerchantModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/merchant")
public class MerchantController {

    private final MerchantService merchantService;

    public MerchantController(
            final MerchantService merchantService
    ) {
        this.merchantService = merchantService;
    }

    @PostMapping
    public ResponseEntity<MerchantModel> save(
            @RequestBody final MerchantModel merchant
    ) {
        return ResponseEntity.ok(merchantService.save(merchant));
    }

    @GetMapping("id/by/product-id/{productId}")
    public ResponseEntity<MerchantModel> getMerchantIdByProductId(
            @PathVariable final long productId
    ) {
        return ResponseEntity.ok(merchantService.getMerchantByProductId(productId));
    }
}
