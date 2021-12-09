package com.fox.shop.shoppingcart.protocol.model.request;

import com.fox.shop.shoppingcart.protocol.types.SessionOriginType;

public class AddToCartRequest implements FatherRequest {
  private CartItemOnCreateRequest cartItem;
  private long sessionId;
  private long userId;
  private SessionOriginType originType;

  public CartItemOnCreateRequest getCartItem() {
    return cartItem;
  }

  public void setCartItem(CartItemOnCreateRequest cartItem) {
    this.cartItem = cartItem;
  }

  public long getSessionId() {
    return sessionId;
  }

  public void setSessionId(long sessionId) {
    this.sessionId = sessionId;
  }

  public SessionOriginType getOriginType() {
    return originType;
  }

  public void setOriginType(SessionOriginType originType) {
    this.originType = originType;
  }

  public long getUserId() {
    return userId;
  }

  public void setUserId(long userId) {
    this.userId = userId;
  }
}
