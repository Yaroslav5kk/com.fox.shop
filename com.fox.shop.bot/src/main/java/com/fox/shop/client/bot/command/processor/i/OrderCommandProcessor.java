package com.fox.shop.client.bot.command.processor.i;

import com.fox.shop.client.bot.model.TgIncomingCommandModel;

public interface OrderCommandProcessor extends CommandProcessor {

    void makeOrderTitle(
        TgIncomingCommandModel incomingCommand
    );

    void setOrderContactInfo(
        TgIncomingCommandModel incomingCommand
    );

    void setOrderContactInfoFromProfileHandle(
        TgIncomingCommandModel incomingCommand
    );
}
