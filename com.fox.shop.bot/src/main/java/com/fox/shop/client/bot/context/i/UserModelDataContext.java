package com.fox.shop.client.bot.context.i;

import com.fox.protocol.user.UserModel;
import com.fox.shop.ordering.protocol.request.OrderOnCreateRequest;
import com.fox.shop.shoppingcart.protocol.model.request.CartItemOnCreateRequest;

import java.util.Optional;

public interface UserModelDataContext {

  void orderOnCreateRequest(
      long userId,
      OrderOnCreateRequest initOrderRequest
  );

  void cartItems(
      long userId,
      CartItemOnCreateRequest cartItem
  );

  OrderOnCreateRequest getOrderOnCreateRequest(long userId);

  CartItemOnCreateRequest getCartItem(long userId);


  void categoryId(
      long userId,
      long categoryId
  );

  long getCategoryIdFromRequest(
      long userId
  );

  void productId(
      long userId,
      long productId
  );

  Long getProductIdFromRequest(
      long userId
  );

  void cartItemId(
      long userId,
      long cartItemId
  );

  Long getCartItemIdFromRequest(
      long userId
  );

  void productGroupId(
      long userId,
      long productGroupId
  );

  Long getProductGroupId(
      long userId
  );

  void registerUserModel(
      long userId,
      UserModel user
  );

  UserModel getRegisterUserModel(
      long userId
  );

  void cartSessionId(
      long userId,
      long cartSessionId
  );

  Optional<Long> getCartSessionId(long userId);


  void clearAll(long userId);
}
