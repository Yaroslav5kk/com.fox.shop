package com.fox.shop.client.bot.ui.generate.i;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.util.Optional;

public interface StartMessageGeneratorMenu {
    SendMessage base(
            Long chatId,
            Optional<Long> cartSessionId
    );

    SendMessage getPhone(
            long chatId
    );

    SendMessage getName(
            long chatId
    );
}
