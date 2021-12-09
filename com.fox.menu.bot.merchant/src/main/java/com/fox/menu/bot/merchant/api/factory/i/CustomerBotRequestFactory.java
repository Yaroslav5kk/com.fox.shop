package com.fox.menu.bot.merchant.api.factory.i;

import org.apache.http.client.methods.HttpUriRequest;

public interface CustomerBotRequestFactory extends FatherRequestFactory {
    /*----------------------------------------------places-------------------------------------------------*/
    HttpUriRequest confirmPlace(
            long userId,
            long placeId
    );
}
