package com.fox.shop.ordering.api.factory.i;

import org.apache.http.client.methods.HttpUriRequest;

import java.util.List;

public interface PriceRequestFactory {
    /*----------------------------------------------products-------------------------------------------------*/
    HttpUriRequest allProductPriceByIds(List<Long> ids);
}
