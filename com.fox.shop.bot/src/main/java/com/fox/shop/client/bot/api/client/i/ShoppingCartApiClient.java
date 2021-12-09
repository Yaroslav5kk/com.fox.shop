package com.fox.shop.client.bot.api.client.i;

import com.fox.shop.shoppingcart.protocol.model.full.FullCartSessionModel;
import com.fox.shop.shoppingcart.protocol.model.request.AddToCartRequest;
import com.fox.shop.shoppingcart.protocol.model.request.CartItemOnCreateRequest;
import com.fox.shop.shoppingcart.protocol.model.response.HasActiveSessionResponse;
import com.fox.shop.shoppingcart.protocol.model.response.SimpleResponse;

public interface ShoppingCartApiClient {

    FullCartSessionModel addToCart(AddToCartRequest request);

    SimpleResponse createCartItem(CartItemOnCreateRequest request);

    FullCartSessionModel getCartSessionById(long sessionId);

    FullCartSessionModel getCartSessionByUser(long userId);

    FullCartSessionModel clearCartSessionByUser(long userId);

    FullCartSessionModel updateCartItemQuantity(
            long cartItemId,
            short newQuantity
    );

    HasActiveSessionResponse hasActiveSession(
            long userId
    );
}
