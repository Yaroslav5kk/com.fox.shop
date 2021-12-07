package com.fox.shop.price.controller;

import com.fox.shop.price.protocol.model.full.PriceModel;
import com.fox.shop.price.protocol.model.full.ProductPriceModel;
import com.fox.shop.price.protocol.model.request.PriceOnCreateRequest;
import com.fox.shop.price.service.PriceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/price")
public class PriceController {

    private final PriceService priceService;

    public PriceController(
            final PriceService priceService
    ) {
        this.priceService = priceService;
    }

    @PostMapping
    public ResponseEntity<PriceModel> save(
            @RequestBody final PriceOnCreateRequest request
    ) {
        return ResponseEntity.ok(priceService.save(request));
    }

    @GetMapping("by-product-id/{productId}")
    public ResponseEntity<ProductPriceModel> getByProductId(
            @PathVariable final long productId,
            @RequestParam(required = false, defaultValue = "1") final int quantity
    ) {
        return ResponseEntity.ok(priceService.getByProductId(productId, quantity));
    }

    @GetMapping("by-product-ids")
    public ResponseEntity<List<ProductPriceModel>> getByProductIds(
            @RequestParam final List<Long> productIds
    ) {
        return ResponseEntity.ok(priceService.getByProductIds(productIds));
    }
}




















