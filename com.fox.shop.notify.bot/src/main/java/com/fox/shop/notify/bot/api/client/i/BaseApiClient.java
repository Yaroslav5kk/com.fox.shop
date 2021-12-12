package com.fox.shop.notify.bot.api.client.i;

import com.fox.shop.protocol.ImageByteModel;
import reactor.core.publisher.Mono;

public interface BaseApiClient {
    Mono<ImageByteModel> downloadImageByteById(long imageId);
}
