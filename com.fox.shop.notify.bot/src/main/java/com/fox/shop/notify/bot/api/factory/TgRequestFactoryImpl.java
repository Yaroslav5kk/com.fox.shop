package com.fox.shop.notify.bot.api.factory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fox.shop.notify.bot.api.factory.i.TgRequestFactory;
import com.fox.shop.notify.bot.model.tg.request.SendPhotoFileIdRequest;
import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Component
public class TgRequestFactoryImpl implements TgRequestFactory {

    @Value("${telegram.api.url}")
    private String basePath;
    @Value("${telegram.api.endpoints.send-message}")
    private String sendMessagePath;
    @Value("${telegram.api.endpoints.send-photo}")
    private String sendPhotoPath;

    @Override
    public WebClient.RequestHeadersSpec<?> sendMessage(
            final WebClient webClient,
            final SendMessage request
    ) {
        return webClient
                .post()
                .uri(buildFullUri(
                        basePath,
                        sendMessagePath,
                        Collections.emptyList()
                ))
                .bodyValue(request);
    }

    @Override
    public WebClient.RequestHeadersSpec<?> sendPhoto(
            final WebClient webClient,
            final SendPhotoFileIdRequest request
    ) {
        return webClient
                .post()
                .uri(buildFullUri(
                        basePath,
                        sendPhotoPath,
                        Collections.emptyList()
                ))
                .bodyValue(request);
    }

/*    @Override
    public WebClient.RequestHeadersSpec<?> sendPhoto(
            final WebClient webClient,
            final SendPhoto sendPhoto
    ) {
        final String fullUri = buildFullUri(
                basePath,
                sendPhotoPath,
                null
        );
        final List<Pair<String, String>> stringParamsKeyValue = new ArrayList<>();
        try {
            stringParamsKeyValue.addAll(Arrays.asList(
                    Pair.of("chat_id", String.valueOf(sendPhoto.getChatId())),
                    Pair.of("caption", Strings.nullToEmpty(sendPhoto.getCaption())),
                    Pair.of("parse_mode", "HTML")
            ));
            ObjectMapper objectMapper = new ObjectMapper();
            if (sendPhoto.getReplyMarkup() != null)
                stringParamsKeyValue.add(Pair.of("reply_markup", objectMapper.writeValueAsString(sendPhoto.getReplyMarkup())));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        final Pair<String, File> keyFile = Pair.of("photo", sendPhoto.getPhoto().getNewMediaFile());
        result.setEntity(buildMultipartFormData(stringParamsKeyValue, Arrays.asList(keyFile)));
        return ;
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
    }*/
}
