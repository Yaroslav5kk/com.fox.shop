package com.fox.shop.client.bot.ui.generate.i;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public interface PaginationMessageGenerator {
    SendMessage pagination(
            long chatId
    );

    boolean isNeedPagination(int amountElements);
}
