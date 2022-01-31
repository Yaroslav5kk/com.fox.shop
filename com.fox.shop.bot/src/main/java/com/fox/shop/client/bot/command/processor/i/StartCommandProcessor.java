package com.fox.shop.client.bot.command.processor.i;

import com.fox.shop.client.bot.model.TgIncomingCommandModel;

public interface StartCommandProcessor extends CommandProcessor {
    void start(
        TgIncomingCommandModel incomingCommand
    );

    void getNameTitle(
        TgIncomingCommandModel incomingCommand
    );

    void getNameHandle(
        TgIncomingCommandModel incomingCommand
    );

    void getPhoneTitle(
        TgIncomingCommandModel incomingCommand
    );

    void getPhoneHandle(
        TgIncomingCommandModel incomingCommand
    );
}
