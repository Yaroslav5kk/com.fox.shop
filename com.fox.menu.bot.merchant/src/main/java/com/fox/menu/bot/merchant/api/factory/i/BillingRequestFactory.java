package com.fox.menu.bot.merchant.api.factory.i;

import org.apache.http.client.methods.HttpUriRequest;

public interface BillingRequestFactory extends FatherRequestFactory {
    /*----------------------------------------------places-------------------------------------------------*/
    HttpUriRequest confirmPlace(
            long userId,
            long placeId
    );
}
