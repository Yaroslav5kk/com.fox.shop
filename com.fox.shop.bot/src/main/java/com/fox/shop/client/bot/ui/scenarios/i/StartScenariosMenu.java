package com.fox.shop.client.bot.ui.scenarios.i;

import com.fox.shop.client.bot.model.TgIncomingCommandModel;
import org.telegram.telegrambots.meta.api.objects.User;

public interface StartScenariosMenu extends Scenarios{
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
