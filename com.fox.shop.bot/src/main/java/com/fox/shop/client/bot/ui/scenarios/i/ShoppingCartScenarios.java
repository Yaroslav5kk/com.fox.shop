package com.fox.shop.client.bot.ui.scenarios.i;

import org.telegram.telegrambots.meta.api.objects.User;

public interface ShoppingCartScenarios extends Scenarios {
    void getCartSession(
            long chatId,
            User user
    );

    void editCartSession(
            long chatId,
            int userId
    );

    void clearCartSession(
            long chatId,
            User user
    );

    void addToCart(
            long chatId,
            int userId,
            long productId
    );

    void setCartItemQuantityOnUpdateTitle(
            long chatId,
            int userId,
            long itemId
    );

    void setCartItemQuantityOnUpdateHandle(
            long chatId,
            int userId,
            short quantity
    );

    void setItemQuantityForAddToCart(
            long chatId,
            int userId,
            int quantity
    );

}
