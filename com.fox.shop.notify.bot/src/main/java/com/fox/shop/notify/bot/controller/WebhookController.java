package com.fox.shop.notify.bot.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.telegram.telegrambots.meta.api.objects.Update;
import reactor.core.publisher.Mono;

@RequestMapping("api/v1/webhook")
@RestController
public class WebhookController {

  @PostMapping("telegram/receive-update")
  public Mono<ResponseEntity<String>> receiveTelegram(@RequestBody final Update update) {
    return null;
  }

  @GetMapping
  public Mono<ResponseEntity<String>> test() {
    return Mono.just(ResponseEntity.ok("keksik"));
  }
}
