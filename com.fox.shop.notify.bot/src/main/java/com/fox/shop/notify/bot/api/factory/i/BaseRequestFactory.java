package com.fox.shop.notify.bot.api.factory.i;

import org.springframework.web.reactive.function.client.WebClient;

public interface BaseRequestFactory {
    /*----------------------------------------------images-------------------------------------------------*/
    WebClient.RequestHeadersSpec<?> downloadImageByteById(
            long id,
            WebClient webClient
    );
}
