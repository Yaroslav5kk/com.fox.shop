package com.fox.shop.base.controller;

import com.fox.shop.base.service.i.ProductBalanceService;
import com.fox.shop.protocol.ProductBalanceModel;
import com.fox.shop.protocol.request.ProductBalanceOnCreateRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/product-balance")
public class ProductBalanceController {

  private final ProductBalanceService productBalanceService;

  public ProductBalanceController(
          final ProductBalanceService productBalanceService
  ) {
    this.productBalanceService = productBalanceService;
  }

  @PostMapping
  public ResponseEntity<ProductBalanceModel> save(
          @RequestBody final ProductBalanceOnCreateRequest request
  ) {
    return ResponseEntity.ok(productBalanceService.save(request));
  }
}










