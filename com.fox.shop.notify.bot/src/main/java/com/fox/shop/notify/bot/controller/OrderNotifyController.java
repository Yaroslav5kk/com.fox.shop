package com.fox.shop.notify.bot.controller;

import com.fox.shop.notify.bot.service.i.OrderNotifyService;
import com.fox.shop.notify.protocol.request.OrderNotifyRequest;
import com.fox.shop.notify.protocol.response.NotifyResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api/v1/notify/order")
public class OrderNotifyController {

  private final OrderNotifyService orderNotifyService;

  public OrderNotifyController(
      final OrderNotifyService orderNotifyService
  ) {
    this.orderNotifyService = orderNotifyService;
  }

  @PostMapping
  public Mono<ResponseEntity<NotifyResponse>> notifyOrder(
      @RequestBody final OrderNotifyRequest request
  ) {
    return orderNotifyService.notifyOrder(request)
        .map(ResponseEntity::ok);
  }

}
