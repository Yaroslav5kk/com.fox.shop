package com.fox.shop.ordering.api.factory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fox.shop.ordering.api.factory.i.FatherRequestFactory;
import com.fox.shop.ordering.api.factory.i.ShoppingCartRequestFactory;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ShoppingCartRequestFactoryImpl implements FatherRequestFactory, ShoppingCartRequestFactory {

    @Value("${shopping-cart.api.url}")
    private String url;
    @Value("${shopping-cart.endpoint.get-cart-session-by-id}")
    private String getCartSessionById;
    @Value("${shopping-cart.endpoint.get-cart-session-by-user}")
    private String getCartSessionByUser;


    private final ObjectMapper objectMapper;


    public ShoppingCartRequestFactoryImpl() {
        objectMapper = new ObjectMapper();
    }

    @Override
    public HttpUriRequest getCartSessionById(final long sessionId) {
        final String fullUri = buildFullUri(
                url,
                getCartSessionById + "/" + sessionId,
                null
        );
        return new HttpGet(fullUri);
    }

}
