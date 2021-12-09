package com.fox.menu.bot.merchant.context.i;

import java.util.LinkedList;

public interface UserHistoryContext {
    void chatIdMessage(
            Long chatId,
            Long messageId
    );

    LinkedList<Long> getAndRemoveMessagesByChatId(Long chatId);

    void snapshot(int userId, String command);

    String back(int userId);
}
