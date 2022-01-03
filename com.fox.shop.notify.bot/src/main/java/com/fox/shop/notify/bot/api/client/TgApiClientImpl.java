package com.fox.shop.notify.bot.api.client;

import com.fox.shop.notify.bot.api.client.i.TgApiClient;
import com.fox.shop.notify.bot.api.factory.i.TgRequestFactory;
import com.fox.shop.notify.bot.model.tg.request.SendPhotoFileIdRequest;
import com.fox.shop.notify.bot.model.tg.response.GeneralTelegramResponse;
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
        return tgRequestFactory.sendMessage(webClient, request).retrieve().bodyToMono(Message.class);
    }

    @Override
    public Mono<Message> sendPhoto(
            final SendPhotoFileIdRequest request
    ) {
        return tgRequestFactory.sendPhoto(webClient, request).retrieve().bodyToMono(GeneralTelegramResponse.class).map(GeneralTelegramResponse::getResult);
    }

    /*@Override
    public Message sendPhoto(
            final SendPhoto sendPhoto
    ) {
        final Optional<GeneralTelegramResponse> response = executeRequestAndExtractResponse(
                tgRequestFactory.sendPhoto(sendPhoto),
                SimpleType.constructUnsafe(GeneralTelegramResponse.class)
        );
        if (response.isPresent()) {
            final Message responseMessage = response.get().getResult();
            userHistoryContext.chatIdMessage(responseMessage.getChatId(), Long.valueOf(responseMessage.getMessageId()));
            return responseMessage;
        }
        return new Message();
    }*/

}





















