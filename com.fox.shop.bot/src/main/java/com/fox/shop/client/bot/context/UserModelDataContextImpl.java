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
  private final Map<Integer, OrderOnCreateRequest> userIdOrderOnCreateRequest;
  private final Map<Integer, CartItemOnCreateRequest> userIdCartItem;
  private final Map<Integer, Long> userIdCategoryIdFromRequest;
  private final Map<Integer, Long> userIdProductGroupIdFromRequest;
  private final Map<Integer, Long> userIdProductIdFromRequest;
  private final Map<Integer, Long> userIdCartItemIdFromRequest;
  private final Map<Integer, UserModel> userIdRegisterUserModel;
  private final Map<Integer, Long> userIdCartSessionId;

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
      final Integer userId,
      final OrderOnCreateRequest order
  ) {
    userIdOrderOnCreateRequest.put(userId, order);
  }

  @Override
  public void cartItems(
      final Integer userId,
      final CartItemOnCreateRequest cartItem
  ) {
    userIdCartItem.put(userId, cartItem);
  }

  @Override
  public OrderOnCreateRequest getOrderOnCreateRequest(final Integer userId) {
    if (!userIdOrderOnCreateRequest.containsKey(userId))
      userIdOrderOnCreateRequest.put(userId, new OrderOnCreateRequest());
    return userIdOrderOnCreateRequest.get(userId);
  }

  @Override
  public CartItemOnCreateRequest getCartItem(final Integer userId) {
    if (!userIdCartItem.containsKey(userId))
      userIdCartItem.put(userId, new CartItemOnCreateRequest());
    return userIdCartItem.get(userId);
  }

  @Override
  public void categoryId(
      final Integer userId,
      final Long categoryId
  ) {
    userIdProductGroupIdFromRequest.put(userId, null);
    userIdCategoryIdFromRequest.put(userId, categoryId);
  }

  @Override
  public Long getCategoryIdFromRequest(
      final Integer userId
  ) {
    return userIdCategoryIdFromRequest.get(userId);
  }

  @Override
  public void productId(
      final Integer userId,
      final Long productId
  ) {
    userIdProductIdFromRequest.put(userId, productId);
  }

  @Override
  public Long getProductIdFromRequest(
      final Integer userId
  ) {
    return userIdProductIdFromRequest.get(userId);
  }

  @Override
  public void cartItemId(
      final Integer userId,
      final Long cartItemId
  ) {
    userIdCartItemIdFromRequest.put(userId, cartItemId);
  }

  @Override
  public Long getCartItemIdFromRequest(
      final Integer userId
  ) {
    return userIdCartItemIdFromRequest.get(userId);
  }

  @Override
  public void productGroupId(
      final Integer userId,
      final long productGroupId
  ) {
    userIdCategoryIdFromRequest.put(userId, null);
    userIdProductGroupIdFromRequest.put(userId, productGroupId);
  }

  @Override
  public Long getProductGroupId(
      final Integer userId
  ) {
    return userIdProductGroupIdFromRequest.get(userId);
  }

  @Override
  public void registerUserModel(
      final Integer userId,
      final UserModel user
  ) {
    userIdRegisterUserModel.put(userId, user);
  }

  @Override
  public UserModel getRegisterUserModel(
      final Integer userId
  ) {
    if (userIdRegisterUserModel.containsKey(userId))
      return userIdRegisterUserModel.get(userId);
    final UserModel user = new UserModel();
    userIdRegisterUserModel.put(userId, user);
    return user;
  }

  @Override
  public void cartSessionId(
      final int userId,
      final long cartSessionId
  ) {
    userIdCartSessionId.put(userId, cartSessionId);
  }

  @Override
  public Optional<Long> getCartSessionId(final int userId) {
    return Optional.ofNullable(userIdCartSessionId.get(userId));
  }

  @Override
  public void clearAll(final int userId){
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
