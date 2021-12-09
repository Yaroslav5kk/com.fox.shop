package com.fox.shop.client.bot.api.factory.i;

import org.apache.http.client.methods.HttpUriRequest;

public interface PriceRequestFactory {
    /*----------------------------------------------price-------------------------------------------------*/
    HttpUriRequest getByProductId(long productId,int quantity);
}
