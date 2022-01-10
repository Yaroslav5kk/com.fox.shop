package com.fox.shop.client.bot.api.factory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fox.shop.client.bot.api.factory.i.FatherRequestFactory;
import com.fox.shop.client.bot.api.factory.i.TelegramRequestFactory;
import com.fox.shop.client.bot.model.request.SendMediaGroupRequest;
import com.fox.shop.client.bot.model.request.SendPhotoFileIdRequest;
import com.google.common.base.Strings;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageCaption;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;

import java.net.URLEncoder;
import java.util.Arrays;
import java.util.Collections;

@Service
public class TelegramRequestFactoryImpl implements TelegramRequestFactory, FatherRequestFactory {

  @Value("${telegram.api.url}")
  private String url;
  @Value("${telegram.endpoint.get-file}")
  private String getFilePath;
  @Value("${telegram.endpoint.set-my-commands}")
  private String setMyCommandsPath;
  @Value("${telegram.endpoint.send-message}")
  private String sendMessagePath;
  @Value("${telegram.endpoint.send-photo}")
  private String sendPhotoPath;
  @Value("${telegram.endpoint.send-media-group}")
  private String sendMediaGroupPath;
  @Value("${telegram.endpoint.delete-message}")
  private String deleteMessagePath;
  @Value("${telegram.endpoint.edit-message-caption}")
  private String editMessageCaptionPath;
  @Value("${telegram.endpoint.edit-message-text}")
  private String editMessageTextPath;

  private final ObjectMapper objectMapper;


  public TelegramRequestFactoryImpl(
  ) {
    objectMapper = new ObjectMapper();
  }

  @Override
  public Request sendMessage(final SendMessage sendMessage) {
    MediaType mediaType = MediaType.parse("application/json");
    RequestBody body = null;
    try {
      body = RequestBody.create(mediaType, objectMapper.writeValueAsString(sendMessage));
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }
    final String fullUri = buildFullUri(
            url,
            sendMessagePath,
            Arrays.asList(
                    Pair.of("chat_id", sendMessage.getChatId()),
                    Pair.of("text", URLEncoder.encode(Strings.nullToEmpty(sendMessage.getText())))
            )
    );
    return new Request.Builder()
            .url(fullUri)
            .method("POST", body)
            .addHeader("Content-Type", "application/json")
            .build();
  }

  @Override
  public Request sendPhoto(
          final SendPhotoFileIdRequest sendPhoto
  ) {
    final String fullUri = buildFullUri(
            url,
            sendPhotoPath,
            null
    );
    MediaType mediaType = MediaType.parse("application/json");
    RequestBody body = null;
    try {
      body = RequestBody.create(mediaType, objectMapper.writeValueAsString(sendPhoto));
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }
    return new Request.Builder()
            .url(fullUri)
            .method("POST", body)
            .addHeader("Content-Type", "application/json")
            .build();
  }

  @Override
  public Request sendMediaGroup(final SendMediaGroupRequest sendMediaGroup) {
    final String fullUri = buildFullUri(
            url,
            sendMediaGroupPath,
            null
    );
    MediaType mediaType = MediaType.parse("application/json");
    RequestBody body = null;
    try {
      body = RequestBody.create(mediaType, objectMapper.writeValueAsString(sendMediaGroup));
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }
    return new Request.Builder()
            .url(fullUri)
            .method("POST", body)
            .addHeader("Content-Type", "application/json")
            .build();
  }

  @Override
  public Request deleteMessage(final long chatId, final long messageId) {
    final String fullUri = buildFullUri(
            url,
            deleteMessagePath,
            Arrays.asList(
                    Pair.of("chat_id", String.valueOf(chatId)),
                    Pair.of("message_id", String.valueOf(messageId))
            )
    );
    return new Request.Builder()
            .url(fullUri)
            .get()
            .addHeader("Content-Type", "application/json")
            .build();
  }

  @Override
  public Request editMessageCaption(final EditMessageCaption editMessageCaption) {
    final String fullUri = buildFullUri(
            url,
            editMessageCaptionPath,
            Collections.emptyList()
    );
    MediaType mediaType = MediaType.parse("application/json");
    RequestBody body = null;
    try {
      body = RequestBody.create(mediaType, objectMapper.writeValueAsString(editMessageCaption));
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }
    return new Request.Builder()
            .url(fullUri)
            .method("POST", body)
            .addHeader("Content-Type", "application/json")
            .build();
  }

  @Override
  public Request editMessageText(final EditMessageText editMessageText) {
    final String fullUri = buildFullUri(
            url,
            editMessageTextPath,
            Collections.emptyList()
    );
    MediaType mediaType = MediaType.parse("application/json");
    RequestBody body = null;
    try {
      body = RequestBody.create(mediaType, objectMapper.writeValueAsString(editMessageText));
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }
    return new Request.Builder()
            .url(fullUri)
            .method("POST", body)
            .addHeader("Content-Type", "application/json")
            .build();
  }

}
