package com.fox.shop.client.bot.ui.scenarios.i;

import com.fox.shop.client.bot.model.TgIncomingCommandModel;
import org.telegram.telegrambots.meta.api.objects.User;

public interface OrderScenarios extends Scenarios {

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
