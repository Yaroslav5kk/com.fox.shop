package com.fox.shop.notify.bot.api.factory;

import com.fox.shop.notify.bot.api.factory.i.FatherRequestFactory;
import com.fox.shop.notify.bot.api.factory.i.StorageRequestFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class StorageRequestFactoryImpl implements FatherRequestFactory, StorageRequestFactory {

    @Value("${storage.api.url}")
    private String url;
    @Value("${storage.endpoint.get-telegram-id-by-base-id}")
    private String getTelegramIdByBaseId;

    /*----------------------------------------------images-------------------------------------------------*/
    @Override
    public WebClient.RequestHeadersSpec<?> getTelegramIdByBaseId(
            final long id,
            final WebClient webClient
    ) {
        final String fullUri = buildFullUri(
                url,
                getTelegramIdByBaseId + "/" + id,
                null
        );
        return webClient.get().uri(fullUri);
    }
}






















