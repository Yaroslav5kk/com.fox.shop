package com.fox.shop.base.controller;

import com.fox.shop.base.service.i.ProductService;
import com.fox.shop.protocol.ProductModel;
import com.fox.shop.protocol.request.ProductOnCreateRequest;
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

    @GetMapping(value = "by/kitchen/{kitchenId}")
    public ResponseEntity<List<ProductModel>> getByKitchen(
            @PathVariable final long kitchenId
    ) {
        return ResponseEntity.ok(productService.getByKitchen(kitchenId));
    }

    @GetMapping(value = "by/group/{groupId}")
    public ResponseEntity<List<ProductModel>> allByGroup(
            @PathVariable final long groupId
    ) {
        return ResponseEntity.ok(productService.allByGroup(groupId));
    }

    @GetMapping(value = "by/ids")
    public ResponseEntity<List<ProductModel>> getByIds(
            @RequestParam final List<Long> ids
    ) {
        return ResponseEntity.ok(productService.byIds(ids));
    }

    @GetMapping(value = "/search/{value}")
    public ResponseEntity<List<ProductModel>> searchByNameMatch(@PathVariable final String value) {
        return ResponseEntity.ok(productService.searchByNameMatch(value));
    }
}
