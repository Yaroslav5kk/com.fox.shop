package com.fox.shop.client.bot.context.model;

import com.fox.shop.ordering.protocol.request.OrderOnCreateRequest;
import com.fox.shop.shoppingcart.protocol.model.request.CartItemOnCreateRequest;

public class UserHistoryStateModel {
    private int userId;
    private String command;
    private UserDomainState domainState;
    private UserProcessState processState;
    private CartItemOnCreateRequest cartItemOnCreateRequest;
    private OrderOnCreateRequest orderOnCreateRequest;

    public UserHistoryStateModel() {
    }

    public UserHistoryStateModel(int userId) {
        this.userId = userId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public UserDomainState getDomainState() {
        return domainState;
    }

    public void setDomainState(UserDomainState domainState) {
        this.domainState = domainState;
    }

    public UserProcessState getProcessState() {
        return processState;
    }

    public void setProcessState(UserProcessState processState) {
        this.processState = processState;
    }

    public CartItemOnCreateRequest getCartItemOnCreateRequest() {
        return cartItemOnCreateRequest;
    }

    public void setCartItemOnCreateRequest(CartItemOnCreateRequest cartItemOnCreateRequest) {
        this.cartItemOnCreateRequest = cartItemOnCreateRequest;
    }

    public OrderOnCreateRequest getOrderOnCreateRequest() {
        return orderOnCreateRequest;
    }

    public void setOrderOnCreateRequest(OrderOnCreateRequest orderOnCreateRequest) {
        this.orderOnCreateRequest = orderOnCreateRequest;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }
}
