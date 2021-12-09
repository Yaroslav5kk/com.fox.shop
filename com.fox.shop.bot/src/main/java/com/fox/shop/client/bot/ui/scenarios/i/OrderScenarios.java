package com.fox.shop.client.bot.ui.scenarios.i;

import org.telegram.telegrambots.meta.api.objects.User;

public interface OrderScenarios extends Scenarios {

    void makeOrderTitle(
            long chatId,
            User user,
            long cartSessionId
    );

    void setOrderContactInfo(
            long chatId,
            User user,
            long cartSessionId
    );

    void setOrderContactInfoFromProfileHandle(
            long chatId,
            User user
    );
}
