package com.fox.shop.client.bot.api.factory.i;

import com.fox.shop.shoppingcart.protocol.model.request.AddToCartRequest;
import com.fox.shop.shoppingcart.protocol.model.request.CartItemOnCreateRequest;
import com.fox.shop.shoppingcart.protocol.types.SessionOriginType;
import org.apache.http.client.methods.HttpUriRequest;

public interface ShoppingCartRequestFactory {
    HttpUriRequest addToCart(AddToCartRequest request);

    HttpUriRequest createCartItem(CartItemOnCreateRequest request);

    HttpUriRequest clearCartSessionByUser(long userId);

    HttpUriRequest getCartSessionByUser(long userId);

    HttpUriRequest getCartSessionById(long sessionId);

    HttpUriRequest updateCartItemQuantity(
            long cartItemId,
            short newQuantity
    );

    HttpUriRequest hasActiveSession(
            long userId,
            SessionOriginType originType
    );
}
