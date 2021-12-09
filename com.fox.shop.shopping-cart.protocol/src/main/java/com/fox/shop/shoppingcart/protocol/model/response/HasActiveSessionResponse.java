package com.fox.shop.shoppingcart.protocol.model.response;

public class HasActiveSessionResponse {
    private long id;
    private boolean isActive;

    public HasActiveSessionResponse() {
    }

    public HasActiveSessionResponse(boolean isActive) {
        this.isActive = isActive;
    }

    public HasActiveSessionResponse(long id, boolean isActive) {
        this.id = id;
        this.isActive = isActive;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
