package com.fox.shop.client.bot.model.types;

import org.springframework.util.StringUtils;

public enum CommandData {
  /*--------------------------------------------------menu/system commands--------------------------------------------------*/
  START("/start", "begin"),
  BACK("/back", "back"),

  /*--------------------------------------------------shopping cart commands--------------------------------------------------*/
  SET_ITEM_QUANTITY_ON_ADD_TO_CART_HANDLE("/set_item_quantity_on_add_to_cart", "new quantity"),
  SET_ITEM_QUANTITY_ON_UPDATE_CART_TITLE("/set_item_quantity_on_update_title", " new quantity"),
  SET_ITEM_QUANTITY_ON_UPDATE_CART_HANDLE("/set_item_quantity_on_update_handle", "new quantity"),
  ADD_TO_CART("/add_to_cart", "додати"),
  GET_CART_SESSION("/get_cart_session", "get cart session"),
  EDIT_CART_SESSION("/edit_cart_session", "items"),
  CLEAN_CART_SESSION("/clean_cart_session", "clean cart session"),

  /*--------------------------------------------------authorize/auth commands--------------------------------------------------*/
  GET_USERNAME_HANDLE("/set_username_handle", "set username handle"),
  GET_PHONE_HANDLE("/get_phone_handle", "set phone"),

  /*--------------------------------------------------order commands--------------------------------------------------*/
  MAKE_ORDER_TITLE("/make_order_title", "make order"),
  SET_ORDER_CONTACT_INFO_TITLE("/set_order_contact_info_title", "make order title"),
  SET_ORDER_CONTACT_INFO_FROM_PROFILE("/set_order_contact_info_from_profile", "from profile"),
  ENTER_ORDER_CONTACT_INFO("/enter_order_contact_info", "enter contact info"),
  ORDER_ON_TIME("/order_on_time", "order on time"),
  ORDER_ON_NAME("/order_on_name", "order on name"),

  /*--------------------------------------------------product commands--------------------------------------------------*/
  PRODUCTS_BY_GROUP("/products_by_group", "переглянути"),
  VIEW_PRODUCT_DESCRIPTION("/view_product_description", "детальніше"),

  /*--------------------------------------------------search commands--------------------------------------------------*/
  SEARCH_TITLE("/search", "search title"),
  SEARCH_HANDLE("/search_handle", "search handle");

  private final String value;
  private final String description;

  CommandData(final String value, final String description) {
    this.value = value;
    this.description = description;
  }

  public static CommandData fromValue(final String value) {
    if (!StringUtils.hasText(value))
      return CommandData.START;
    for (final var itCommand : values())
      if (value.equals(itCommand.getValue()))
        return itCommand;
    return CommandData.START;
  }

  public final String getValue() {
    return value;
  }

  public String getDescription() {
    return description;
  }

  public static boolean contains(final String data) {
    for (var el : values()) {
      if (el.getValue().equals(data)) {
        return true;
      }
    }
    return false;
  }

}
