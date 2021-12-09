package com.fox.shop.shoppingcart.base.api;

import java.net.http.HttpRequest;

public interface BaseRequestFactory {
    HttpRequest productPriceById(long productId);

    HttpRequest productById(long productId);

    HttpRequest merchantByProductId(long productId);
}
