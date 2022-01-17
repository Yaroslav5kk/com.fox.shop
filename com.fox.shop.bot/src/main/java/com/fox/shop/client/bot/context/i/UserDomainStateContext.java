package com.fox.shop.client.bot.context.i;

import com.fox.shop.client.bot.model.types.UserDomainState;

public interface UserDomainStateContext {
  void setup(
          int userId,
          UserDomainState domainState
  );

  void start(Integer userId);

    void finish(Integer userId);

    void setName(Integer userId);

    void setPhone(Integer userId);

    void makeOrderTitle(Integer userId);

    void initOrder(Integer userId);

    void setOrderContactInfoFromProfile(Integer userId);

    void enterOrderContactInfo(Integer userId);

    void setOrderDeliveryInfoFromProfile(Integer userId);

    void setOrderContactInfoTitle(Integer userId);

    void orderOnName(Integer userId);

    void orderOnTime(Integer userId);

    void getCartSession(Integer userId);

    void viewCartSessionProduct(Integer userId);

    void editCartSession(Integer userId);

    void addToCart(Integer userId);

    void pagination(Integer userId);

    void productsByGroup(Integer userId);

    void allSearchProductGroups(Integer userId);

  void viewProductDescription(Integer userId);

  void allMainProductGroups(Integer userId);

    void searchTitle(Integer userId);

    void searchProduct(Integer userId);

    void clearCartSession(Integer userId);

    void setItemQuantityOnAddToCart(Integer userId);

    void setItemQuantityOnUpdateHandle(Integer userId);

    void setItemQuantityOnUpdateTitle(Integer userId);

    UserDomainState current(Integer userId);

    void put(Integer userId, UserDomainState domainState);

}
