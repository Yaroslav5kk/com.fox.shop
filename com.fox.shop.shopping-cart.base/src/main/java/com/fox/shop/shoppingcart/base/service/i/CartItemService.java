package com.fox.shop.shoppingcart.base.service.i;

import com.fox.shop.shoppingcart.protocol.model.request.CartItemOnCreateRequest;
import com.fox.shop.shoppingcart.protocol.model.response.SimpleResponse;

public interface CartItemService {
  SimpleResponse save(CartItemOnCreateRequest request);
}
