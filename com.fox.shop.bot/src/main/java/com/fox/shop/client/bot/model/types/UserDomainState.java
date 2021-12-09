package com.fox.shop.client.bot.model.types;

public enum UserDomainState {
    START(CommandData.START),
    FINISH(CommandData.FINISH),
    RESET(CommandData.RESET),
    BACK(CommandData.BACK),
    SET_NAME(CommandData.SET_NAME),
    SET_PHONE(CommandData.SET_PHONE),
    PRODUCT_BY_CATEGORY(CommandData.PRODUCT_BY_CATEGORY),
    MAKE_ORDER_TITLE(CommandData.MAKE_ORDER_TITLE),
    INIT_ORDER(CommandData.INIT_ORDER),
    SET_ORDER_CONTACT_INFO_TITLE(CommandData.SET_ORDER_CONTACT_INFO_TITLE),
    SET_ORDER_CONTACT_INFO_FROM_PROFILE(CommandData.SET_ORDER_CONTACT_INFO_FROM_PROFILE),
    ENTER_ORDER_CONTACT_INFO(CommandData.ENTER_ORDER_CONTACT_INFO),
    SET_ORDER_DELIVERY_INFO_TITLE(CommandData.SET_ORDER_DELIVERY_INFO_TITLE),
    SET_ORDER_ADDRESS_FROM_PROFILE(CommandData.SET_ORDER_ADDRESS_FROM_PROFILE),
    ENTER_ORDER_ADDRESS(CommandData.ENTER_ORDER_ADDRESS),
    ORDER_ON_TIME(CommandData.ORDER_ON_TIME),
    ORDER_ON_NAME(CommandData.ORDER_ON_NAME),
    GET_CART_SESSION(CommandData.GET_CART_SESSION),
    VIEW_CART_SESSION_PRODUCT(CommandData.VIEW_CART_SESSION_PRODUCT),
    EDIT_CART_SESSION(CommandData.EDIT_CART_SESSION),
    CLEAN_CART_SESSION(CommandData.CLEAR_CART_SESSION),
    SET_ITEM_QUANTITY_ON_ADD_TO_CART(CommandData.SET_ITEM_QUANTITY_ON_ADD_TO_CART),
    SET_ITEM_QUANTITY_ON_UPDATE_TITLE(CommandData.SET_ITEM_QUANTITY_ON_UPDATE_TITLE),
    SET_ITEM_QUANTITY_ON_UPDATE_HANDLE(CommandData.SET_ITEM_QUANTITY_ON_UPDATE_HANDLE),
    ADD_TO_CART(CommandData.ADD_TO_CART),
    PAGINATION(CommandData.PAGINATION),
    PRODUCTS_BY_GROUP(CommandData.PRODUCTS_BY_GROUP),
    ALL_SEARCH_PRODUCT_GROUPS(CommandData.ALL_SEARCH_PRODUCT_GROUPS),
    VIEW_PRODUCT_DESCRIPTION(CommandData.VIEW_PRODUCT_DESCRIPTION),
    ALL_MAIN_PRODUCT_GROUPS(CommandData.ALL_MAIN_PRODUCT_GROUPS),
    SEARCH_TITLE(CommandData.SEARCH_TITLE),
    SEARCH_PRODUCT(CommandData.SEARCH_PRODUCT);

    private final CommandData command;

    UserDomainState(final CommandData command) {
        this.command = command;
    }

    public CommandData getCommand() {
        return command;
    }

    public static UserDomainState fromCommand(final CommandData command) {
        for (var itState : values()) {
            if (itState.getCommand().equals(command))
                return itState;
        }
        return START;
    }

    public static boolean isExists(final CommandData command) {
        for (var itState : values()) {
            if (itState.getCommand().equals(command)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isMain(final CommandData command) {
        if (!isExists(command))
            return false;
        return true;
    }
}
