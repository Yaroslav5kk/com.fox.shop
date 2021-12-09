package com.fox.shop.notify.controller;

import com.fox.shop.notify.protocol.request.OrderNotifyRequest;
import com.fox.shop.notify.protocol.response.NotifyResponse;
import com.fox.shop.notify.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api/v1/order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(
            final OrderService orderService
    ) {
        this.orderService = orderService;
    }

    @PostMapping
    public Mono<ResponseEntity<NotifyResponse>> order(
            @RequestBody final OrderNotifyRequest request
    ) {
        return orderService.notifyOrder(request).map(ResponseEntity::ok);
    }

}
