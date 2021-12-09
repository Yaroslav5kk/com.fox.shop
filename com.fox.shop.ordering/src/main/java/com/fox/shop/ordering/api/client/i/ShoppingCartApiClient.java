package com.fox.shop.ordering.api.client.i;

import com.fox.shop.shoppingcart.protocol.model.full.FullCartSessionModel;

public interface ShoppingCartApiClient {
    FullCartSessionModel getCartSessionById(long sessionId);

}
