package com.fox.shop.shoppingcart.protocol.model.request;

import com.fox.shop.shoppingcart.protocol.types.SessionOriginType;

public class CartSessionOnCreateRequest implements FatherRequest {
    private long userId;
    private SessionOriginType originType;
    private long itemId;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public SessionOriginType getOriginType() {
        return originType;
    }

    public void setOriginType(SessionOriginType originType) {
        this.originType = originType;
    }

    public long getItemId() {
        return itemId;
    }

    public void setItemId(long itemId) {
        this.itemId = itemId;
    }
}
