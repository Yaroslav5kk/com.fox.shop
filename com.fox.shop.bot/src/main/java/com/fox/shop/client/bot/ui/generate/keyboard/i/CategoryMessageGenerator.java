package com.fox.shop.client.bot.ui.generate.keyboard.i;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public interface CategoryMessageGenerator {
    SendMessage afterAllCategory(
            long chatId,
            int userId
    );
}
