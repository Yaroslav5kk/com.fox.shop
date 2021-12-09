package com.fox.shop.client.bot.ui.generate.i;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public interface StartMessageGeneratorMenu {
    SendMessage base(
            Long chatId
    );

    SendMessage getPhone(
            long chatId
    );

    SendMessage getName(
            long chatId
    );
}
