package com.fox.shop.base.controller;

import com.fox.shop.base.service.i.ProductService;
import com.fox.shop.protocol.ProductModel;
import com.fox.shop.protocol.request.ProductOnCreateRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/product")
public class ProductController {
  private final ProductService productService;

  public ProductController(
      final ProductService productService
  ) {
    this.productService = productService;
  }

  @PostMapping
  public ResponseEntity<ProductModel> save(@RequestBody final ProductOnCreateRequest request) {
    return ResponseEntity.ok(productService.save(request));
  }

  @GetMapping("{id}")
  public ResponseEntity<ProductModel> get(
      @PathVariable final long id
  ) {
    return ResponseEntity.ok(productService.get(id));
  }

  @GetMapping(value = "by/group/{groupId}")
  public ResponseEntity<Page<ProductModel>> allByGroup(
      @PathVariable final long groupId,
      final Pageable pageable
  ) {
    return ResponseEntity.ok(productService.allByGroup(groupId, pageable));
  }

  @GetMapping(value = "by/ids")
  public ResponseEntity<Page<ProductModel>> getByIds(
      @RequestParam final List<Long> ids,
      final Pageable pageable
  ) {
    return ResponseEntity.ok(productService.byIds(ids, pageable));
  }

  @GetMapping(value = "/search/{value}")
  public ResponseEntity<Page<ProductModel>> searchByNameMatch(
      @PathVariable final String value,
      final Pageable pageable
  ) {
    return ResponseEntity.ok(productService.searchByNameMatch(value, pageable));
  }
}
