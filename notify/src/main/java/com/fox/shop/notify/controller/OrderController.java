package com.fox.shop.notify.controller;

import com.fox.shop.notify.protocol.response.NotifyResponse;
import com.fox.shop.notify.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

  @PostMapping("{orderId}")
  public Mono<ResponseEntity<NotifyResponse>> order(
      @PathVariable final long orderId
  ) {
    return orderService.notifyOrder(orderId).map(ResponseEntity::ok);
  }

}
