package com.fox.shop.client.bot.context.i;

import com.fox.shop.ordering.protocol.request.OrderOnCreateRequest;
import com.fox.shop.shoppingcart.protocol.model.request.CartItemOnCreateRequest;
import com.fox.protocol.user.UserModel;

import java.util.Optional;

public interface UserModelDataContext {

    void orderOnCreateRequest(
            Integer userId,
            OrderOnCreateRequest initOrderRequest
    );

    void cartItems(
            Integer userId,
            CartItemOnCreateRequest cartItem
    );

    OrderOnCreateRequest getOrderOnCreateRequest(Integer userId);

    CartItemOnCreateRequest getCartItem(Integer userId);

    void cleanAll(Integer userId);

    void categoryId(
            Integer userId,
            Long categoryId
    );

    Long getCategoryIdFromRequest(
            Integer userId
    );

    void productId(
            Integer userId,
            Long productId
    );

    Long getProductIdFromRequest(
            Integer userId
    );

    void cartItemId(
            Integer userId,
            Long cartItemId
    );

    Long getCartItemIdFromRequest(
            Integer userId
    );

    void productGroupId(
            Integer userId,
            long productGroupId
    );

    Long getProductGroupId(
            Integer userId
    );

    void registerUserModel(
            Integer userId,
            UserModel user
    );

    UserModel getRegisterUserModel(
            Integer userId
    );

  void cartSessionId(
      int userId,
      long cartSessionId
  );

    Optional<Long> getCartSessionId(int userId);
}
