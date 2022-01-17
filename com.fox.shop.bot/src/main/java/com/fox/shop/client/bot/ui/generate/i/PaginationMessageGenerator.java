package com.fox.shop.client.bot.ui.generate.i;

import com.fox.shop.client.bot.model.types.CommandData;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public interface PaginationMessageGenerator {
    SendMessage pagination(
            long chatId,
            String commandData
    );
}
