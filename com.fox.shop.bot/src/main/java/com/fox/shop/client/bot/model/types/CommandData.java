package com.fox.shop.client.bot.model.types;

import org.springframework.util.StringUtils;

public enum CommandData {
  START("/start", "begin"),
  MAIN("/main", "main board"),
  FINISH("/finish", "finish"),
  RESET("/reset", "reset"),
  BACK("/back", "back"),
  PLUS_ONE("/plus_one", "+1"),
  MINUS_ONE("/minus_one", "-1"),
  DELETE_CART_ITEM("/delete_cart_item", "delete"),
  SET_ITEM_QUANTITY_ON_ADD_TO_CART("/set_item_quantity_on_add_to_cart", "new quantity"),
  SET_ITEM_QUANTITY_ON_UPDATE_TITLE("/set_item_quantity_on_update_title", " new quantity"),
  SET_ITEM_QUANTITY_ON_UPDATE_HANDLE("/set_item_quantity_on_update_handle", "new quantity"),
  SET_VALUE("/set_value", "set value"),
  SET_NAME("/set_name", "set name"),
  SET_PHONE("/set_phone", "set phone"),
  SET_URL("/set_url", "set url"),
  ENTER_OTHER_VALUE("/enter_other", "enter other"),
  NEXT_PAGE("/next_page", "next"),
  PREVIOUS_PAGE("/previous_page", "previous"),
  SET_PRIORITY("/set_priority", "set priority"),
  ADD_START_DATE("/add_start_date", "add start date"),
  ADD_END_DATE("/add_end_date", "add end date"),
  ADD_TO_CART("/add_to_cart", "додати"),
  PRODUCT_BY_CATEGORY("/product_by_category", "products"),
  MAKE_ORDER_TITLE("/make_order_title", "make order"),
  SET_ORDER_CONTACT_INFO_TITLE("/set_order_contact_info_title", "make order title"),
  SET_ORDER_CONTACT_INFO_FROM_PROFILE("/set_order_contact_info_from_profile", "from profile"),
  ENTER_ORDER_CONTACT_INFO("/enter_order_contact_info", "enter contact info"),
  SET_ORDER_DELIVERY_INFO_TITLE("/set_order_delivery_info_title", "make order title"),
  SET_ORDER_ADDRESS_FROM_PROFILE("/set_order_address_from_profile", "from profile"),
  ENTER_ORDER_ADDRESS("/enter_order_address", "enter address"),
  INIT_ORDER("/init_order", "init order"),
  ORDER_ON_TIME("/order_on_time", "order on time"),
  ORDER_ON_TIME_TITLE("/order_on_time_title", "order on time title"),
  ORDER_ON_NAME("/order_on_name", "order on name"),
  ORDER_ON_NAME_TITLE("/order_on_name_title", "order on name title"),
  EDIT_CART_SESSION("/edit_cart_session", "items"),
  GET_CART_SESSION("/get_cart_session", "get cart session"),
  VIEW_CART_SESSION_PRODUCT("/view_cart_session_product", "view items"),
  CLEAR_CART_SESSION("/clean_cart_session", "clean cart session"),
  PAGINATION("/pagination", "go to 'X' page"),
  PRODUCTS_BY_GROUP("/products_by_group", "переглянути"),
  ALL_SEARCH_PRODUCT_GROUPS("/all_search_product_groups", "all search product groups"),
  VIEW_PRODUCT_DESCRIPTION("/view_product_description", "детальніше"),
  ALL_MAIN_PRODUCT_GROUPS("/all_main_product_groups", "all main product groups"),
  SEARCH_TITLE("/search", "search"),
  SEARCH_PRODUCT("/SEARCH_PRODUCT", "search product");

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
