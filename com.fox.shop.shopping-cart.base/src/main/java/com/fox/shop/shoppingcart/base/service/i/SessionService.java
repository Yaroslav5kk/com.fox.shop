package com.fox.shop.shoppingcart.base.service.i;

import com.fox.shop.shoppingcart.protocol.model.full.FullCartSessionModel;
import com.fox.shop.shoppingcart.protocol.model.request.AddToCartRequest;
import com.fox.shop.shoppingcart.protocol.model.request.CartSessionOnCreateRequest;
import com.fox.shop.shoppingcart.protocol.model.response.HasActiveSessionResponse;
import com.fox.shop.shoppingcart.protocol.types.SessionOriginType;

public interface SessionService {

    void save(CartSessionOnCreateRequest request);

    FullCartSessionModel cleanSession(
            long userId,
            SessionOriginType originType
    );

    FullCartSessionModel get(
            Long id,
            Long userId,
            SessionOriginType originType
    );

    FullCartSessionModel addToCart(AddToCartRequest request);

    FullCartSessionModel updateItemQuantity(
            long itemId,
            short newQuantity
    );

    HasActiveSessionResponse hasActiveSession(
            long userId,
            SessionOriginType originType
    );
}
