package com.fox.shop.ordering.controller;

import com.fox.shop.ordering.protocol.model.OrderModel;
import com.fox.shop.ordering.protocol.request.OrderOnCreateRequest;
import com.fox.shop.ordering.service.i.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(
            final OrderService orderService
    ) {
        this.orderService = orderService;
    }

    @PostMapping("init")
    public ResponseEntity<OrderModel> initOrder(
            @RequestBody final OrderOnCreateRequest request
    ) {
        return ResponseEntity.ok(orderService.initOrder(request));
    }

    @GetMapping("{id}")
    public ResponseEntity<OrderModel> get(
            @PathVariable final String id
    ) {
        return ResponseEntity.ok(orderService.get(id));
    }
}


