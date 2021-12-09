package com.fox.shop.ordering.api.factory.i;

import org.apache.http.client.methods.HttpUriRequest;

public interface ShoppingCartRequestFactory {
    HttpUriRequest getCartSessionById(long sessionId);

}
