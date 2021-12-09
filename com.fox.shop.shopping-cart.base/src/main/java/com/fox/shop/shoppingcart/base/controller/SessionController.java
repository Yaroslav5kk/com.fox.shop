package com.fox.shop.shoppingcart.base.controller;

import com.fox.shop.shoppingcart.base.service.i.SessionService;
import com.fox.shop.shoppingcart.protocol.model.full.FullCartSessionModel;
import com.fox.shop.shoppingcart.protocol.model.request.AddToCartRequest;
import com.fox.shop.shoppingcart.protocol.model.response.HasActiveSessionResponse;
import com.fox.shop.shoppingcart.protocol.types.SessionOriginType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/session")
public class SessionController {

    private final SessionService sessionService;

    public SessionController(
            final SessionService sessionService
    ) {
        this.sessionService = sessionService;
    }

    @GetMapping(value = {"","/{id}"})
    public ResponseEntity<FullCartSessionModel> get(
            @PathVariable(required = false) final Long id,
            @RequestParam(required = false) final Long userId,
            @RequestParam(required = false) final SessionOriginType originType
    ) {
        return ResponseEntity.ok(sessionService.get(id, userId, originType));
    }

    @PutMapping("clear")
    public ResponseEntity<FullCartSessionModel> clearSession(
            @RequestParam final long userId,
            @RequestParam final SessionOriginType originType
    ) {
        return ResponseEntity.ok(sessionService.cleanSession(userId, originType));
    }

    @PostMapping("add-to-cart")
    public ResponseEntity<FullCartSessionModel> addToCart(@RequestBody final AddToCartRequest request) {
        return ResponseEntity.ok(sessionService.addToCart(request));
    }

    @PutMapping("item-quantity")
    public ResponseEntity<FullCartSessionModel> updateItemQuantity(
            @RequestParam final long cartItemId,
            @RequestParam final short newQuantity
    ) {
        return ResponseEntity.ok(sessionService.updateItemQuantity(cartItemId, newQuantity));
    }

    @GetMapping("has-active-session")
    public ResponseEntity<HasActiveSessionResponse> hasActiveSession(
            @RequestParam final long userId,
            @RequestParam final SessionOriginType originType
    ) {
        return ResponseEntity.ok(sessionService.hasActiveSession(userId, originType));
    }
}
