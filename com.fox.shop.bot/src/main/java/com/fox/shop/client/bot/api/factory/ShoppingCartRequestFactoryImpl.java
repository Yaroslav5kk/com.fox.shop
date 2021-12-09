package com.fox.shop.client.bot.api.factory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fox.shop.client.bot.api.factory.i.FatherRequestFactory;
import com.fox.shop.client.bot.api.factory.i.ShoppingCartRequestFactory;
import com.fox.shop.shoppingcart.protocol.model.request.AddToCartRequest;
import com.fox.shop.shoppingcart.protocol.model.request.CartItemOnCreateRequest;
import com.fox.shop.shoppingcart.protocol.types.SessionOriginType;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

@Component
public class ShoppingCartRequestFactoryImpl implements ShoppingCartRequestFactory, FatherRequestFactory {
    @Value("${shopping-cart.api.url}")
    private String url;
    @Value("${shopping-cart.api.endpoint.get-cart-session}")
    private String getCartSession;
    @Value("${shopping-cart.api.endpoint.get-cart-session-by-id}")
    private String getCartSessionById;
    @Value("${shopping-cart.api.endpoint.add-to-cart}")
    private String addToCart;
    @Value("${shopping-cart.api.endpoint.create-cart-item}")
    private String createCartItem;
    @Value("${shopping-cart.api.endpoint.clear-cart-session}")
    private String clearCartSession;
    @Value("${shopping-cart.api.endpoint.update-cart-item-quantity}")
    private String updateCartItemQuantity;
    @Value("${shopping-cart.api.endpoint.has-active-session}")
    private String hasActiveSession;

    private final ObjectMapper objectMapper;


    public ShoppingCartRequestFactoryImpl(
    ) {
        objectMapper = new ObjectMapper();
    }


    @Override
    public HttpUriRequest addToCart(final AddToCartRequest request) {
        final String fullUri = buildFullUri(
                url,
                addToCart,
                null
        );
        final HttpPost result = new HttpPost(fullUri);
        result.setHeader("Content-Type", ContentType.APPLICATION_JSON.getMimeType());
        try {
            result.setEntity(new StringEntity(objectMapper.writeValueAsString(request)));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return result;
    }


    @Override
    public HttpUriRequest createCartItem(final CartItemOnCreateRequest request) {
        final String fullUri = buildFullUri(
                url,
                createCartItem,
                null
        );
        final HttpPost result = new HttpPost(fullUri);
        result.setHeader("Content-Type", ContentType.APPLICATION_JSON.getMimeType());
        try {
            result.setEntity(new StringEntity(objectMapper.writeValueAsString(request)));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public HttpUriRequest clearCartSessionByUser(final long userId) {
        final String fullUri = buildFullUri(
                url,
                clearCartSession,
                Arrays.asList(Pair.of("userId", String.valueOf(userId)), Pair.of("originType", SessionOriginType.TELEGRAM.name()))
        );
        return new HttpPut(fullUri);
    }

    @Override
    public HttpUriRequest getCartSessionByUser(final long userId) {
        final String fullUri = buildFullUri(
                url,
                getCartSession,
                Arrays.asList(Pair.of("userId", String.valueOf(userId)), Pair.of("originType", SessionOriginType.TELEGRAM.name()))
        );
        return new HttpGet(fullUri);
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

    @Override
    public HttpUriRequest updateCartItemQuantity(
            final long cartItemId,
            final short newQuantity
    ) {
        final String fullUri = buildFullUri(
                url,
                updateCartItemQuantity,
                Arrays.asList(
                        Pair.of("cartItemId", String.valueOf(cartItemId)),
                        Pair.of("newQuantity", String.valueOf(newQuantity))
                )
        );
        return new HttpPut(fullUri);
    }

    @Override
    public HttpUriRequest hasActiveSession(
            final long userId,
            final SessionOriginType originType
    ) {
        final String fullUri = buildFullUri(
                url,
                hasActiveSession,
                Arrays.asList(
                        Pair.of("userId", String.valueOf(userId)),
                        Pair.of("originType", originType.name())
                )
        );
        return new HttpGet(fullUri);
    }

}
