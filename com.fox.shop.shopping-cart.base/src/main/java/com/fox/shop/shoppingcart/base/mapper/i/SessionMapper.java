package com.fox.shop.shoppingcart.base.mapper.i;

import com.fox.shop.shoppingcart.base.entity.SessionEntity;
import com.fox.shop.shoppingcart.protocol.model.full.FullCartSessionModel;
import com.fox.shop.shoppingcart.protocol.model.request.CartSessionOnCreateRequest;

public interface SessionMapper {
    SessionEntity fromRequestToEntity(CartSessionOnCreateRequest request);

    FullCartSessionModel entityToModel(SessionEntity entity);

    SessionEntity fromRequestToEntity(long userId, long itemId);
}
