package com.fox.shop.client.bot.ui.scenarios.i;

import org.telegram.telegrambots.meta.api.objects.User;

public interface StartScenariosMenu extends Scenarios{
    void base(
            Long chatId,
            final User user
    );

    void getNameTitle(
            long chatId,
            int userId
    );

    void getNameHandle(
            long chatId,
            String name,
            int userId
    );

    void getPhoneTitle(
            long chatId,
            int userId
    );

    void getPhoneHandle(
            long chatId,
            User user,
            String phone
    );
}
