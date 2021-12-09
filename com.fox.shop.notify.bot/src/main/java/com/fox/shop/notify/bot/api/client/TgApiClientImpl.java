package com.fox.shop.notify.bot.api.client;

import com.fox.shop.notify.bot.api.client.i.TgApiClient;
import com.fox.shop.notify.bot.api.factory.i.TgRequestFactory;
import com.fox.shop.notify.protocol.request.OrderNotifyRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import reactor.core.publisher.Mono;

@Component
public class TgApiClientImpl implements TgApiClient {

  private final TgRequestFactory tgRequestFactory;
  private final WebClient webClient;

  public TgApiClientImpl(
      final TgRequestFactory tgRequestFactory
  ) {
    this.tgRequestFactory = tgRequestFactory;
    webClient = WebClient.create();
  }

  @Override
  public Mono<Message> sendMessage(
      final SendMessage request
  ) {
    return tgRequestFactory.sendMessage(webClient,request).retrieve().bodyToMono(Message.class);
  }

}





















