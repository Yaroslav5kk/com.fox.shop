package com.fox.menu.bot.merchant.api.factory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fox.menu.bot.merchant.api.factory.i.FatherRequestFactory;
import com.fox.menu.bot.merchant.api.factory.i.TelegramRequestFactory;
import com.fox.menu.bot.merchant.model.request.SendMediaGroupRequest;
import com.fox.menu.bot.merchant.model.request.SendPhotoFileIdRequest;
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

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class TelegramRequestFactoryImpl implements FatherRequestFactory, TelegramRequestFactory {

    @Value("${telegram.api.url}")
    private String url;
    @Value("${telegram.endpoint.send-message}")
    private String sendMessagePath;
    @Value("${telegram.endpoint.send-photo}")
    private String sendPhotoPath;
    @Value("${telegram.endpoint.send-media-group}")
    private String sendMediaGroupPath;
    @Value("${telegram.endpoint.delete-message}")
    private String deleteMessagePath;

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
            final SendPhoto sendPhoto
    ) {
        final String fullUri = buildFullUri(
                url,
                sendPhotoPath,
                null
        );
        final HttpPost result = new HttpPost(fullUri);
        final List<Pair<String, String>> stringParamsKeyValue = new ArrayList<>();
        try {
            stringParamsKeyValue.addAll(Arrays.asList(
                    Pair.of("chat_id", String.valueOf(sendPhoto.getChatId())),
                    Pair.of("caption", Strings.nullToEmpty(sendPhoto.getCaption())),
                    Pair.of("parse_mode", "HTML")
            ));
            if (sendPhoto.getReplyMarkup() != null)
                stringParamsKeyValue.add(Pair.of("reply_markup", objectMapper.writeValueAsString(sendPhoto.getReplyMarkup())));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        final Pair<String, File> keyFile = Pair.of("photo", sendPhoto.getPhoto().getNewMediaFile());
        result.setEntity(buildMultipartFormData(stringParamsKeyValue, Arrays.asList(keyFile)));
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
            stringParamsKeyValue = Arrays.asList(
                    Pair.of("chat_id", String.valueOf(sendPhoto.getChatId())),
                    Pair.of("caption", sendPhoto.getCaption()),
                    Pair.of("parse_mode", "HTML"),
                    Pair.of("photo", sendPhoto.getPhoto())
            );
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
        result.setEntity(buildMultipartFormData(stringParamsKeyValue, sendMediaGroup.getNameFile()));
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
