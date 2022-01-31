package com.fox.shop.client.bot.events;

import com.fox.shop.client.bot.api.mediator.TelegramApiMediator;
import com.fox.shop.client.bot.context.i.PaginationDataContext;
import com.fox.shop.client.bot.entity.TgChatMessageHistoryEntity;
import com.fox.shop.client.bot.model.request.TgDeleteMessageRequest;
import com.fox.shop.client.bot.service.i.TgChatMessageHistoryService;
import com.fox.shop.protocol.response.PageResponse;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TgRemoveMessagesApplicationEventHandler {

  private final TgChatMessageHistoryService chatMessageHistoryService;
  private final TelegramApiMediator telegramApiMediator;

  public TgRemoveMessagesApplicationEventHandler(
          final TgChatMessageHistoryService chatMessageHistoryService,
          final TelegramApiMediator telegramApiMediator
  ) {
    this.chatMessageHistoryService = chatMessageHistoryService;
    this.telegramApiMediator = telegramApiMediator;
  }

  @EventListener(TgRemoveMessagesApplicationEvent.class)
  public void handle(final TgRemoveMessagesApplicationEvent event) {
    removeMessages(event);
  }

  private void removeMessages(final TgRemoveMessagesApplicationEvent event) {
    final List<TgChatMessageHistoryEntity> byUserId = chatMessageHistoryService.getByUserId(event.getUserId());
    for (var history : byUserId) {
      for (var messageId : history.getMessagesIds()) {
        telegramApiMediator.addMessage(new TgDeleteMessageRequest(history.getChatId(), messageId));
      }
    }
    chatMessageHistoryService.deleteAll(byUserId);
  }

}
































