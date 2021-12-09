package com.fox.shop.shoppingcart.base.controller;

import com.fox.shop.shoppingcart.base.service.i.CartItemService;
import com.fox.shop.shoppingcart.protocol.model.request.CartItemOnCreateRequest;
import com.fox.shop.shoppingcart.protocol.model.response.SimpleResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/cart-item")
public class CartItemController {

    private final CartItemService cartItemService;

    public CartItemController(
            final CartItemService cartItemService
    ) {
        this.cartItemService = cartItemService;
    }

    @PostMapping
    public ResponseEntity<SimpleResponse> save(@RequestBody final CartItemOnCreateRequest request) {
        return ResponseEntity.ok(cartItemService.save(request));
    }
}
