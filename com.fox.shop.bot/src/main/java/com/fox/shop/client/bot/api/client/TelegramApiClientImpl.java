package com.fox.shop.client.bot.api.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.SimpleType;
import com.fox.shop.client.bot.api.client.i.FatherApiClient;
import com.fox.shop.client.bot.api.client.i.TelegramApiClient;
import com.fox.shop.client.bot.api.factory.i.TelegramRequestFactory;
import com.fox.shop.client.bot.context.i.UserHistoryContext;
import com.fox.shop.client.bot.model.request.SendMediaGroupRequest;
import com.fox.shop.client.bot.model.request.SendPhotoFileIdRequest;
import com.fox.shop.client.bot.model.response.DeleteMessageResponse;
import com.fox.shop.client.bot.model.response.GeneralTelegramResponse;
import com.fox.shop.client.bot.model.response.SendMediaGroupResponse;
import org.apache.http.impl.client.CloseableHttpClient;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageCaption;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class TelegramApiClientImpl implements TelegramApiClient, FatherApiClient {

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
  public List<Message> sendMediaGroup(final SendMediaGroupRequest sendMediaGroup) {
    Optional<SendMediaGroupResponse> response = executeRequestAndExtractResponse(
        telegramRequestFactory.sendMediaGroup(sendMediaGroup),
        SimpleType.constructUnsafe(SendMediaGroupResponse.class)
    );

    if (response.isPresent()) {
      final List<Message> responseMessage = response.get().getResult();
      responseMessage.forEach(message -> userHistoryContext.chatIdMessage(message.getChatId(), Long.valueOf(message.getMessageId())));
      return responseMessage;
    }
    return Collections.emptyList();
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
  public Message editMessageCaption(final EditMessageCaption editMessageCaption) {
    Optional<Message> response = executeRequestAndExtractResponse(
        telegramRequestFactory.editMessageCaption(editMessageCaption),
        SimpleType.constructUnsafe(Message.class)
    );

    return response.isPresent()
        ? response.get()
        : new Message();
  }

  @Override
  public Message editMessageText(final EditMessageText editMessageText) {
    Optional<Message> response = executeRequestAndExtractResponse(
        telegramRequestFactory.editMessageText(editMessageText),
        SimpleType.constructUnsafe(Message.class)
    );

    return response.isPresent()
        ? response.get()
        : new Message();
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
