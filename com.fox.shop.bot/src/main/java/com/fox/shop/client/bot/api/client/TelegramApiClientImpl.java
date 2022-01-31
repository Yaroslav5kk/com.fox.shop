package com.fox.shop.client.bot.api.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fox.shop.client.bot.api.client.i.FatherApiClient;
import com.fox.shop.client.bot.api.client.i.TelegramApiClient;
import com.fox.shop.client.bot.api.factory.i.TelegramRequestFactory;
import com.fox.shop.client.bot.entity.TgChatMessageHistoryEntity;
import com.fox.shop.client.bot.model.request.SendMediaGroupRequest;
import com.fox.shop.client.bot.model.request.SendPhotoFileIdRequest;
import com.fox.shop.client.bot.model.request.TgDeleteMessageRequest;
import com.fox.shop.client.bot.model.response.DeleteMessageResponse;
import com.fox.shop.client.bot.model.response.GeneralTelegramResponse;
import com.fox.shop.client.bot.model.response.SendMediaGroupResponse;
import com.squareup.okhttp.OkHttpClient;
import org.apache.http.impl.client.CloseableHttpClient;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageCaption;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class TelegramApiClientImpl implements TelegramApiClient, FatherApiClient {

  private final TelegramRequestFactory telegramRequestFactory;
  private final ObjectMapper objectMapper;
  private final OkHttpClient client;

  public TelegramApiClientImpl(
          final TelegramRequestFactory telegramRequestFactory,
          final ObjectMapper objectMapper
  ) {
    this.telegramRequestFactory = telegramRequestFactory;
    this.objectMapper = objectMapper;
    this.client = new OkHttpClient();
  }

  @Override
  public Message sendPhoto(
          final SendPhotoFileIdRequest sendPhoto
  ) {
    try {
      Message result = objectMapper.readValue(
              client.newCall(telegramRequestFactory.sendPhoto(sendPhoto)).execute().body().byteStream(), GeneralTelegramResponse.class
      ).getResult();
      return result;
    } catch (IOException e) {
      e.printStackTrace();
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
    try {
      Message result = objectMapper.readValue(
              client.newCall(telegramRequestFactory.sendMessage(sendMessage)).execute().body().byteStream(), GeneralTelegramResponse.class
      ).getResult();
      return result;
    } catch (IOException e) {
      e.printStackTrace();
    }
    return new Message();
  }


  @Override
  public List<Message> sendMediaGroup(final SendMediaGroupRequest sendMediaGroup) {
    try {
      List<Message> result = objectMapper.readValue(
              client.newCall(telegramRequestFactory.sendMediaGroup(sendMediaGroup)).execute().body().byteStream(), SendMediaGroupResponse.class
      ).getResult();
      return result;
    } catch (IOException e) {
      e.printStackTrace();
    }
    return Collections.emptyList();
  }

  @Override
  public DeleteMessageResponse deleteMessage(final TgDeleteMessageRequest deleteMessageRequest) {
    try {
      return objectMapper.readValue(
              client.newCall(telegramRequestFactory.deleteMessage(
                      deleteMessageRequest.getChatId(),
                      deleteMessageRequest.getMessageId()
              )).execute().body().byteStream(), DeleteMessageResponse.class
      );
    } catch (IOException e) {
      e.printStackTrace();
    }
    return DeleteMessageResponse.failed();
  }

  @Override
  public Message editMessageCaption(final EditMessageCaption editMessageCaption) {
    try {
      Message result = objectMapper.readValue(
              client.newCall(telegramRequestFactory.editMessageCaption(editMessageCaption)).execute().body().byteStream(), GeneralTelegramResponse.class
      ).getResult();
      return result;
    } catch (IOException e) {
      e.printStackTrace();
    }
    return new Message();
  }

  @Override
  public Message editMessageText(final EditMessageText editMessageText) {
    try {
      Message result = objectMapper.readValue(
              client.newCall(telegramRequestFactory.editMessageText(editMessageText)).execute().body().byteStream(), GeneralTelegramResponse.class
      ).getResult();
      return result;
    } catch (IOException e) {
      e.printStackTrace();
    }
    return new Message();
  }

  @Override
  public CloseableHttpClient getClient() {
    return null;
  }

  @Override
  public ObjectMapper getObjectMapper() {
    return objectMapper;
  }
}
