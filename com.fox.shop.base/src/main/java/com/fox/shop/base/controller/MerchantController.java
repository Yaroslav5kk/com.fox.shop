package com.fox.shop.base.controller;

import com.fox.shop.base.service.MerchantService;
import com.fox.shop.protocol.response.GeneralResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/merchant")
public class MerchantController {

  private final MerchantService merchantService;

  public MerchantController(
      final MerchantService merchantService
  ) {
    this.merchantService = merchantService;
  }

  @GetMapping("id/by/product-id/{productId}")
  public ResponseEntity<GeneralResponse> getMerchantIdByProductId(
      @PathVariable final long productId
  ) {
    return ResponseEntity.ok(merchantService.getMerchantIdByProductId(productId));
  }
}
