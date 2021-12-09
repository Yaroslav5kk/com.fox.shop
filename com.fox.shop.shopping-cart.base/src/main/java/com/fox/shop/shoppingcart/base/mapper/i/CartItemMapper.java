package com.fox.shop.shoppingcart.base.mapper.i;

import com.fox.shop.shoppingcart.base.entity.CartItemEntity;
import com.fox.shop.shoppingcart.protocol.model.full.FullCartItemModel;
import com.fox.shop.shoppingcart.protocol.model.request.CartItemOnCreateRequest;

public interface CartItemMapper {
  CartItemEntity fromRequestToEntity(CartItemOnCreateRequest request);

  FullCartItemModel entityToModel(CartItemEntity entity);
}
