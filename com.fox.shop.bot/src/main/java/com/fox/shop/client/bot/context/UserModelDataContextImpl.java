package com.fox.shop.client.bot.context;

import com.fox.protocol.user.UserModel;
import com.fox.shop.client.bot.context.i.UserModelDataContext;
import com.fox.shop.ordering.protocol.request.OrderOnCreateRequest;
import com.fox.shop.shoppingcart.protocol.model.request.CartItemOnCreateRequest;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class UserModelDataContextImpl implements UserModelDataContext {
  private final Map<Long, OrderOnCreateRequest> userIdOrderOnCreateRequest;
  private final Map<Long, CartItemOnCreateRequest> userIdCartItem;
  private final Map<Long, Long> userIdCategoryIdFromRequest;
  private final Map<Long, Long> userIdProductGroupIdFromRequest;
  private final Map<Long, Long> userIdProductIdFromRequest;
  private final Map<Long, Long> userIdCartItemIdFromRequest;
  private final Map<Long, UserModel> userIdRegisterUserModel;
  private final Map<Long, Long> userIdCartSessionId;

  public UserModelDataContextImpl() {
    userIdOrderOnCreateRequest = new HashMap<>();
    userIdCartItem = new HashMap<>();
    userIdCategoryIdFromRequest = new HashMap<>();
    userIdProductIdFromRequest = new HashMap<>();
    userIdCartItemIdFromRequest = new HashMap<>();
    userIdProductGroupIdFromRequest = new HashMap<>();
    userIdRegisterUserModel = new HashMap<>();
    userIdCartSessionId = new HashMap<>();
  }

  @Override
  public void orderOnCreateRequest(
          final long userId,
          final OrderOnCreateRequest order
  ) {
    userIdOrderOnCreateRequest.put(userId, order);
  }

  @Override
  public void cartItems(
          final long userId,
          final CartItemOnCreateRequest cartItem
  ) {
    userIdCartItem.put(userId, cartItem);
  }

  @Override
  public OrderOnCreateRequest getOrderOnCreateRequest(final long userId) {
    if (!userIdOrderOnCreateRequest.containsKey(userId))
      userIdOrderOnCreateRequest.put(userId, new OrderOnCreateRequest());
    return userIdOrderOnCreateRequest.get(userId);
  }

  @Override
  public CartItemOnCreateRequest getCartItem(final long userId) {
    if (!userIdCartItem.containsKey(userId))
      userIdCartItem.put(userId, new CartItemOnCreateRequest());
    return userIdCartItem.get(userId);
  }

  @Override
  public void categoryId(
          final long userId,
          final long categoryId
  ) {
    userIdProductGroupIdFromRequest.put(userId, null);
    userIdCategoryIdFromRequest.put(userId, categoryId);
  }

  @Override
  public long getCategoryIdFromRequest(
          final long userId
  ) {
    return userIdCategoryIdFromRequest.get(userId);
  }

  @Override
  public void productId(
          final long userId,
          final long productId
  ) {
    userIdProductIdFromRequest.put(userId, productId);
  }

  @Override
  public Long getProductIdFromRequest(
          final long userId
  ) {
    return userIdProductIdFromRequest.get(userId);
  }

  @Override
  public void cartItemId(
          final long userId,
          final long cartItemId
  ) {
    userIdCartItemIdFromRequest.put(userId, cartItemId);
  }

  @Override
  public Long getCartItemIdFromRequest(
          final long userId
  ) {
    return userIdCartItemIdFromRequest.get(userId);
  }

  @Override
  public void productGroupId(
          final long userId,
          final long productGroupId
  ) {
    userIdCategoryIdFromRequest.put(userId, null);
    userIdProductGroupIdFromRequest.put(userId, productGroupId);
  }

  @Override
  public Long getProductGroupId(
          final long userId
  ) {
    return userIdProductGroupIdFromRequest.get(userId);
  }

  @Override
  public void registerUserModel(
          final long userId,
          final UserModel user
  ) {
    userIdRegisterUserModel.put(userId, user);
  }

  @Override
  public UserModel getRegisterUserModel(
          final long userId
  ) {
    if (userIdRegisterUserModel.containsKey(userId))
      return userIdRegisterUserModel.get(userId);
    final UserModel user = new UserModel();
    userIdRegisterUserModel.put(userId, user);
    return user;
  }

  @Override
  public void cartSessionId(
          final long userId,
          final long cartSessionId
  ) {
    userIdCartSessionId.put(userId, cartSessionId);
  }

  @Override
  public Optional<Long> getCartSessionId(final long userId) {
    return Optional.ofNullable(userIdCartSessionId.get(userId));
  }

  @Override
  public void clearAll(final long userId) {
    userIdOrderOnCreateRequest.remove(userId);
    userIdCartItem.remove(userId);
    userIdCartSessionId.remove(userId);
    userIdRegisterUserModel.remove(userId);
    userIdCategoryIdFromRequest.remove(userId);
    userIdCartItemIdFromRequest.remove(userId);
    userIdProductGroupIdFromRequest.remove(userId);
    userIdProductIdFromRequest.remove(userId);
  }
}
