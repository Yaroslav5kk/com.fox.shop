package com.fox.shop.client.bot.command.processor.i;

import com.fox.shop.client.bot.model.TgIncomingCommandModel;

public interface ShoppingCartCommandProcessor extends CommandProcessor {
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

    void setItemQuantityOnAddToCartHandle(
        TgIncomingCommandModel incomingCommand
    );

}
