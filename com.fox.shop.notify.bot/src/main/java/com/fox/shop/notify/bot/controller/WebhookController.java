package com.fox.shop.notify.bot.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.telegram.telegrambots.meta.api.objects.Update;
import reactor.core.publisher.Mono;

@RequestMapping("api/v1/webhook")
@RestController
public class WebhookController {

  @PostMapping("telegram/receive-update")
  public Mono<ResponseEntity<String>> receiveTelegram(@RequestBody final Update update) {
    return null;
  }
}
