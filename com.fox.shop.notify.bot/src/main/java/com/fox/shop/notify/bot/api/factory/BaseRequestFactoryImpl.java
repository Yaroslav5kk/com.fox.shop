package com.fox.shop.notify.bot.api.factory;

import com.fox.shop.notify.bot.api.factory.i.BaseRequestFactory;
import com.fox.shop.notify.bot.api.factory.i.FatherRequestFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class BaseRequestFactoryImpl implements FatherRequestFactory, BaseRequestFactory {

    @Value("${base.api.url}")
    private String url;
    @Value("${base.endpoint.download-image-byte-by-id}")
    private String downloadImageByteById;

    /*----------------------------------------------images-------------------------------------------------*/
    @Override
    public WebClient.RequestHeadersSpec<?> downloadImageByteById(
            final long id,
            final WebClient webClient
    ) {
        final String fullUri = buildFullUri(
                url,
                downloadImageByteById + "/" + id,
                null
        );
        return webClient.get().uri(fullUri);
    }
}






















