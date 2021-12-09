package com.fox.menu.bot.merchant.api.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.SimpleType;
import com.fox.menu.bot.merchant.api.client.i.FatherApiClient;
import com.fox.menu.bot.merchant.api.client.i.TelegramApiClient;
import com.fox.menu.bot.merchant.api.factory.i.TelegramRequestFactory;
import com.fox.menu.bot.merchant.context.i.UserHistoryContext;
import com.fox.menu.bot.merchant.model.request.SendMediaGroupRequest;
import com.fox.menu.bot.merchant.model.request.SendPhotoFileIdRequest;
import com.fox.menu.bot.merchant.model.response.DeleteMessageResponse;
import com.fox.menu.bot.merchant.model.response.GeneralTelegramResponse;
import org.apache.http.impl.client.CloseableHttpClient;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TelegramApiClientImpl implements FatherApiClient, TelegramApiClient {

    private final TelegramRequestFactory telegramRequestFactory;
    private final CloseableHttpClient client;
    private final ObjectMapper objectMapper;
    private final UserHistoryContext userHistoryContext;

    public TelegramApiClientImpl(
            final TelegramRequestFactory telegramRequestFactory,
            final CloseableHttpClient client,
            final ObjectMapper objectMapper,
            final UserHistoryContext userHistoryContext
    ) {
        this.telegramRequestFactory = telegramRequestFactory;
        this.client = client;
        this.objectMapper = objectMapper;
        this.userHistoryContext = userHistoryContext;
    }

    @Override
    public Message sendPhoto(
            final SendPhotoFileIdRequest sendPhoto
    ) {
        final Optional<GeneralTelegramResponse> response = executeRequestAndExtractResponse(
                telegramRequestFactory.sendPhoto(sendPhoto),
                SimpleType.constructUnsafe(GeneralTelegramResponse.class)
        );
        if (response.isPresent()) {
            final Message responseMessage = response.get().getResult();
            userHistoryContext.chatIdMessage(responseMessage.getChatId(), Long.valueOf(responseMessage.getMessageId()));
            return responseMessage;
        }
        return new Message();
    }

    @Override
    public Message sendPhoto(
            final SendPhoto sendPhoto
    ) {
        final Optional<GeneralTelegramResponse> response = executeRequestAndExtractResponse(
                telegramRequestFactory.sendPhoto(sendPhoto),
                SimpleType.constructUnsafe(GeneralTelegramResponse.class)
        );
        if (response.isPresent()) {
            final Message responseMessage = response.get().getResult();
            userHistoryContext.chatIdMessage(responseMessage.getChatId(), Long.valueOf(responseMessage.getMessageId()));
            return responseMessage;
        }
        return new Message();
    }

    @Override
    public List<Message> sendMessage(final List<SendMessage> sendMessages) {
        final List<Message> result = new ArrayList<>();
        sendMessages.forEach(sendMessage -> result.add(sendMessage(sendMessage)));
        return result;
    }

    @Override
    public Message sendMessage(final SendMessage sendMessage) {
        Optional<GeneralTelegramResponse> response = executeRequestAndExtractResponse(
                telegramRequestFactory.sendMessage(sendMessage),
                SimpleType.constructUnsafe(GeneralTelegramResponse.class)
        );

        if (response.isPresent()) {
            final Message responseMessage = response.get().getResult();
            userHistoryContext.chatIdMessage(responseMessage.getChatId(), Long.valueOf(responseMessage.getMessageId()));
            return responseMessage;
        }
        return new Message();
    }


    @Override
    public Message sendMediaGroup(final SendMediaGroupRequest sendMediaGroup) {
        Optional<GeneralTelegramResponse> response = executeRequestAndExtractResponse(
                telegramRequestFactory.sendMediaGroup(sendMediaGroup),
                SimpleType.constructUnsafe(GeneralTelegramResponse.class)//objectMapper.getTypeFactory().constructCollectionType(List.class, MessageEntity.class)
        );

        if (response.isPresent()) {
            final Message responseMessage = response.get().getResult();
            userHistoryContext.chatIdMessage(responseMessage.getChatId(), Long.valueOf(responseMessage.getMessageId()));
            return responseMessage;
        }
        return new Message();
    }

    @Override
    public DeleteMessageResponse deleteMessage(final long chatId, final long messageId) {
        Optional<DeleteMessageResponse> response = executeRequestAndExtractResponse(
                telegramRequestFactory.deleteMessage(chatId, messageId),
                SimpleType.constructUnsafe(DeleteMessageResponse.class)
        );

        return response.isPresent()
                ? response.get()
                : DeleteMessageResponse.failed();
    }


    @Override
    public CloseableHttpClient getClient() {
        return client;
    }

    @Override
    public ObjectMapper getObjectMapper() {
        return objectMapper;
    }
}
