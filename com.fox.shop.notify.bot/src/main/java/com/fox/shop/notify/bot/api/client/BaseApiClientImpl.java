package com.fox.shop.notify.bot.api.client;

import com.fox.shop.notify.bot.api.client.i.BaseApiClient;
import com.fox.shop.notify.bot.api.client.i.FatherApiClient;
import com.fox.shop.notify.bot.api.factory.i.BaseRequestFactory;
import com.fox.shop.protocol.ImageByteModel;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class BaseApiClientImpl implements FatherApiClient, BaseApiClient {


    private final BaseRequestFactory baseRequestFactory;
    private final WebClient webClient;

    public BaseApiClientImpl(
            final BaseRequestFactory baseRequestFactory
    ) {
        this.baseRequestFactory = baseRequestFactory;
        webClient = WebClient.create();
    }

    @Override
    public Mono<ImageByteModel> downloadImageByteById(final long imageId) {
        return baseRequestFactory.downloadImageByteById(imageId,webClient).retrieve().bodyToMono(ImageByteModel.class);
    }
}
