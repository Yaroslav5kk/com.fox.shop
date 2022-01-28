package com.fox.shop.client.bot.model;

import com.fox.protocol.user.UserModel;
import com.fox.shop.client.bot.model.types.CommandData;
import com.fox.shop.ordering.protocol.request.OrderOnCreateRequest;
import com.fox.shop.shoppingcart.protocol.model.request.CartItemOnCreateRequest;

public class TgUserSessionModel {
  private CommandData command = CommandData.START;
  private OrderOnCreateRequest orderOnCreateRequest = new OrderOnCreateRequest();
  private CartItemOnCreateRequest cartItemOnCreateRequest = new CartItemOnCreateRequest();
  private UserModel userModel = new UserModel();
  private long productGroupId = 0;
  private long cartItemId = 0;
  private long cartSessionId = 0;


  public OrderOnCreateRequest getOrderOnCreateRequest() {
    return orderOnCreateRequest;
  }

  public void setOrderOnCreateRequest(OrderOnCreateRequest orderOnCreateRequest) {
    this.orderOnCreateRequest = orderOnCreateRequest;
  }

  public CartItemOnCreateRequest getCartItemOnCreateRequest() {
    return cartItemOnCreateRequest;
  }

  public void setCartItemOnCreateRequest(CartItemOnCreateRequest cartItemOnCreateRequest) {
    this.cartItemOnCreateRequest = cartItemOnCreateRequest;
  }

  public UserModel getUserModel() {
    return userModel;
  }

  public void setUserModel(UserModel userModel) {
    this.userModel = userModel;
  }

  public long getProductGroupId() {
    return productGroupId;
  }

  public void setProductGroupId(long productGroupId) {
    this.productGroupId = productGroupId;
  }

  public long getCartItemId() {
    return cartItemId;
  }

  public void setCartItemId(long cartItemId) {
    this.cartItemId = cartItemId;
  }

  public long getCartSessionId() {
    return cartSessionId;
  }

  public void setCartSessionId(long cartSessionId) {
    this.cartSessionId = cartSessionId;
  }

  public CommandData getCommand() {
    return command;
  }

  public void setCommand(CommandData command) {
    this.command = command;
  }
}
