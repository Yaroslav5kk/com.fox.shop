package com.fox.shop.client.bot.api.factory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fox.shop.client.bot.api.factory.i.FatherRequestFactory;
import com.fox.shop.client.bot.api.factory.i.TelegramRequestFactory;
import com.fox.shop.client.bot.model.request.SendMediaGroupRequest;
import com.fox.shop.client.bot.model.request.SendPhotoFileIdRequest;
import com.google.common.base.Strings;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageCaption;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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
  public HttpUriRequest sendMessage(final SendMessage sendMessage) {
    final String fullUri = buildFullUri(
        url,
        sendMessagePath,
        Arrays.asList(
            Pair.of("chat_id", sendMessage.getChatId()),
            Pair.of("text", URLEncoder.encode(Strings.nullToEmpty(sendMessage.getText())))
        )
    );
    final HttpPost result = new HttpPost(fullUri);
    String body;
    try {
      body = objectMapper.writeValueAsString(sendMessage);
      result.setEntity(new StringEntity(body));
      result.setHeader("Accept", "application/json");
      result.setHeader("Content-type", "application/json");
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }
    return result;
  }

  @Override
  public HttpUriRequest sendPhoto(
      final SendPhotoFileIdRequest sendPhoto
  ) {
    final String fullUri = buildFullUri(
        url,
        sendPhotoPath,
        null
    );
    final HttpPost result = new HttpPost(fullUri);
    List<Pair<String, String>> stringParamsKeyValue = new ArrayList<>();
    try {
      stringParamsKeyValue.addAll(Arrays.asList(
          Pair.of("chat_id", String.valueOf(sendPhoto.getChatId())),
          Pair.of("caption", sendPhoto.getCaption()),
          Pair.of("parse_mode", "HTML"),
          Pair.of("photo", sendPhoto.getPhoto())
      ));
      if (sendPhoto.getReplyMarkup() != null)
        stringParamsKeyValue.add(Pair.of("reply_markup", objectMapper.writeValueAsString(sendPhoto.getReplyMarkup())));
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }
    result.setEntity(buildMultipartFormData(stringParamsKeyValue, Collections.emptyList()));
    return result;
  }

  @Override
  public HttpUriRequest sendMediaGroup(final SendMediaGroupRequest sendMediaGroup) {
    final String fullUri = buildFullUri(
        url,
        sendMediaGroupPath,
        null
    );
    final HttpPost result = new HttpPost(fullUri);
    List<Pair<String, String>> stringParamsKeyValue = new ArrayList<>();
    try {
      stringParamsKeyValue = Arrays.asList(
          Pair.of("chat_id", String.valueOf(sendMediaGroup.getChatId())),
          Pair.of("media", objectMapper.writeValueAsString(sendMediaGroup.getMedia()))
      );
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }
    result.setEntity(buildMultipartFormData(stringParamsKeyValue, Collections.emptyList()));
    return result;
  }

  @Override
  public HttpUriRequest deleteMessage(final long chatId, final long messageId) {
    final String fullUri = buildFullUri(
        url,
        deleteMessagePath,
        Arrays.asList(
            Pair.of("chat_id", String.valueOf(chatId)),
            Pair.of("message_id", String.valueOf(messageId))
        )
    );
    return new HttpGet(fullUri);
  }

  @Override
  public HttpUriRequest editMessageCaption(final EditMessageCaption editMessageCaption) {
    final String fullUri = buildFullUri(
        url,
        editMessageCaptionPath,
        Collections.emptyList()
    );
    final HttpPost result = new HttpPost(fullUri);
    String body;
    try {
      body = objectMapper.writeValueAsString(editMessageCaption);
      result.setEntity(new StringEntity(body));
      result.setHeader("Accept", "application/json");
      result.setHeader("Content-type", "application/json");
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }
    return result;
  }

  @Override
  public HttpUriRequest editMessageText(final EditMessageText editMessageText) {
    final String fullUri = buildFullUri(
        url,
        editMessageTextPath,
        Collections.emptyList()
    );
    final HttpPost result = new HttpPost(fullUri);
    String body;
    try {
      body = objectMapper.writeValueAsString(editMessageText);
      result.setEntity(new StringEntity(body));
      result.setHeader("Accept", "application/json");
      result.setHeader("Content-type", "application/json");
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }
    return result;
  }

  private HttpEntity buildMultipartFormData(
      final List<Pair<String, String>> stringParamsKeyValue,
      final List<Pair<String, File>> keyFile
  ) {
    final MultipartEntityBuilder result = MultipartEntityBuilder.create();
    stringParamsKeyValue.forEach(keyValue ->
        result.addTextBody(keyValue.getFirst(), keyValue.getSecond()));
    keyFile.forEach(nameFIle -> result.addBinaryBody(nameFIle.getFirst(), nameFIle.getSecond()));
    return result.build();
  }

}
