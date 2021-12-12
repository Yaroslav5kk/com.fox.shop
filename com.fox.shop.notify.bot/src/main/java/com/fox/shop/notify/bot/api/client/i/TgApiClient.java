package com.fox.shop.notify.bot.api.client.i;

import com.fox.shop.notify.bot.model.tg.request.SendPhotoFileIdRequest;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import reactor.core.publisher.Mono;

public interface TgApiClient extends FatherApiClient {
  Mono<Message> sendMessage(
      SendMessage request
  );

    Mono<Message> sendPhoto(
            SendPhotoFileIdRequest request
    );
}
