package com.fox.shop.client.bot.context.i;

import java.util.LinkedList;

public interface UserHistoryContext {
  void productIdMessageId(
      Long productId,
      Long messageId
  );

  void chatIdMessage(
            Long chatId,
            Long messageId
    );

    LinkedList<Long> getAndRemoveMessagesByChatId(Long chatId);

    void snapshot(int userId, String command);

    String back(int userId);
}
