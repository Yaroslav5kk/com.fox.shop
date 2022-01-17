package com.fox.shop.client.bot.context;

import com.fox.shop.client.bot.context.i.UserDomainStateContext;
import com.fox.shop.client.bot.model.types.UserDomainState;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserDomainStateContextImpl implements UserDomainStateContext {
  private final Map<Integer, UserDomainState> userDomainStateMap;

  public UserDomainStateContextImpl() {
    userDomainStateMap = new HashMap<>();
  }

  @Override
  public void setup(
          final int userId,
          final UserDomainState domainState
  ) {
    userDomainStateMap.put(userId, domainState);
  }

  @Override
  public void start(final Integer userId) {
    this.userDomainStateMap.put(userId, UserDomainState.START);
  }

  @Override
  public void finish(final Integer userId) {
    this.userDomainStateMap.put(userId, UserDomainState.FINISH);
  }

  @Override
  public void setName(final Integer userId) {
    this.userDomainStateMap.put(userId, UserDomainState.SET_NAME);
  }

  @Override
  public void setPhone(final Integer userId) {
    this.userDomainStateMap.put(userId, UserDomainState.SET_PHONE);
  }

  @Override
  public void makeOrderTitle(final Integer userId) {
    this.userDomainStateMap.put(userId, UserDomainState.MAKE_ORDER_TITLE);
  }

  @Override
  public void initOrder(final Integer userId) {
    this.userDomainStateMap.put(userId, UserDomainState.INIT_ORDER);
  }

  @Override
  public void setOrderContactInfoFromProfile(final Integer userId) {
    this.userDomainStateMap.put(userId, UserDomainState.SET_ORDER_CONTACT_INFO_FROM_PROFILE);
  }

  @Override
  public void enterOrderContactInfo(final Integer userId) {
    this.userDomainStateMap.put(userId, UserDomainState.ENTER_ORDER_CONTACT_INFO);
  }

  @Override
  public void setOrderDeliveryInfoFromProfile(final Integer userId) {
    this.userDomainStateMap.put(userId, UserDomainState.SET_ORDER_ADDRESS_FROM_PROFILE);
  }

  @Override
  public void setOrderContactInfoTitle(final Integer userId) {
    this.userDomainStateMap.put(userId, UserDomainState.SET_ORDER_CONTACT_INFO_TITLE);
  }

  @Override
  public void orderOnName(final Integer userId) {
    this.userDomainStateMap.put(userId, UserDomainState.ORDER_ON_NAME);
  }

  @Override
  public void orderOnTime(final Integer userId) {
    this.userDomainStateMap.put(userId, UserDomainState.ORDER_ON_TIME);
  }

  @Override
  public void getCartSession(final Integer userId) {
    this.userDomainStateMap.put(userId, UserDomainState.GET_CART_SESSION);
  }

  @Override
  public void viewCartSessionProduct(final Integer userId) {
    this.userDomainStateMap.put(userId, UserDomainState.VIEW_CART_SESSION_PRODUCT);
  }

  @Override
  public void editCartSession(final Integer userId) {
    this.userDomainStateMap.put(userId, UserDomainState.EDIT_CART_SESSION);
  }

  @Override
  public void addToCart(final Integer userId) {
    this.userDomainStateMap.put(userId, UserDomainState.ADD_TO_CART);
  }

  @Override
  public void pagination(final Integer userId) {
    this.userDomainStateMap.put(userId, UserDomainState.PAGINATION);
  }

  @Override
  public void productsByGroup(final Integer userId) {
    this.userDomainStateMap.put(userId, UserDomainState.PRODUCTS_BY_GROUP);
  }

  @Override
  public void allSearchProductGroups(final Integer userId) {
    this.userDomainStateMap.put(userId, UserDomainState.ALL_SEARCH_PRODUCT_GROUPS);
  }

  @Override
  public void viewProductDescription(final Integer userId) {
    this.userDomainStateMap.put(userId, UserDomainState.VIEW_PRODUCT_DESCRIPTION);
  }

  @Override
  public void allMainProductGroups(final Integer userId) {
    this.userDomainStateMap.put(userId, UserDomainState.ALL_MAIN_PRODUCT_GROUPS);
  }

  @Override
  public void searchTitle(final Integer userId) {
    this.userDomainStateMap.put(userId, UserDomainState.SEARCH_TITLE);
  }

  @Override
  public void searchProduct(final Integer userId) {
    this.userDomainStateMap.put(userId, UserDomainState.SEARCH_PRODUCT);
  }

  @Override
  public void clearCartSession(final Integer userId) {
    userDomainStateMap.put(userId, UserDomainState.CLEAN_CART_SESSION);
  }

  @Override
  public void setItemQuantityOnAddToCart(final Integer userId) {
    userDomainStateMap.put(userId, UserDomainState.SET_ITEM_QUANTITY_ON_ADD_TO_CART);
  }

  @Override
  public void setItemQuantityOnUpdateHandle(final Integer userId) {
    userDomainStateMap.put(userId, UserDomainState.SET_ITEM_QUANTITY_ON_UPDATE_HANDLE);
  }

  @Override
  public void setItemQuantityOnUpdateTitle(final Integer userId) {
    userDomainStateMap.put(userId, UserDomainState.SET_ITEM_QUANTITY_ON_UPDATE_TITLE);
  }

  @Override
  public UserDomainState current(final Integer userId) {
    if (userDomainStateMap.containsKey(userId))
      return userDomainStateMap.get(userId);
    userDomainStateMap.put(userId, UserDomainState.START);
    return UserDomainState.START;
  }

  @Override
  public void put(final Integer userId, final UserDomainState domainState) {
    userDomainStateMap.put(userId, domainState);
  }


}
