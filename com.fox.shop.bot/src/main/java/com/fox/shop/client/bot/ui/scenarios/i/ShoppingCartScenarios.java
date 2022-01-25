package com.fox.shop.client.bot.ui.scenarios.i;

import com.fox.shop.client.bot.model.TgIncomingCommandModel;
import org.telegram.telegrambots.meta.api.objects.User;

public interface ShoppingCartScenarios extends Scenarios {
    void getCartSession(
        TgIncomingCommandModel incomingCommand
    );

    void editCartSession(
        TgIncomingCommandModel incomingCommand
    );

    void clearCartSession(
        TgIncomingCommandModel incomingCommand
    );

    void addToCart(
        TgIncomingCommandModel incomingCommand
    );

    void setCartItemQuantityOnUpdateTitle(
        TgIncomingCommandModel incomingCommand
    );

    void setCartItemQuantityOnUpdateHandle(
        TgIncomingCommandModel incomingCommand
    );

    void setItemQuantityForAddToCart(
        TgIncomingCommandModel incomingCommand
    );

}
