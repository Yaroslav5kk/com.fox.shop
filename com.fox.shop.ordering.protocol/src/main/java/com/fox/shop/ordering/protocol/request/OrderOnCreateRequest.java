package com.fox.shop.ordering.protocol.request;

import com.fox.shop.ordering.protocol.types.OrderOriginType;

public class OrderOnCreateRequest {
    private long shoppingCartSessionId;
    private String telegramUsername;
    private String phone;
    private String address;
    private String firstname;
    private String lastname;
    private OrderOriginType originType;

    public OrderOnCreateRequest() {
    }

    public OrderOnCreateRequest(long shoppingCartSessionId) {
        this.shoppingCartSessionId = shoppingCartSessionId;
    }

    public OrderOnCreateRequest(long shoppingCartSessionId, String phone, String address, String firstname, String lastname, OrderOriginType originType) {
        this.shoppingCartSessionId = shoppingCartSessionId;
        this.phone = phone;
        this.address = address;
        this.firstname = firstname;
        this.lastname = lastname;
        this.originType = originType;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public OrderOriginType getOriginType() {
        return originType;
    }

    public void setOriginType(OrderOriginType originType) {
        this.originType = originType;
    }

    public long getShoppingCartSessionId() {
        return shoppingCartSessionId;
    }

    public void setShoppingCartSessionId(long shoppingCartSessionId) {
        this.shoppingCartSessionId = shoppingCartSessionId;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getTelegramUsername() {
        return telegramUsername;
    }

    public void setTelegramUsername(String telegramUsername) {
        this.telegramUsername = telegramUsername;
    }
}
