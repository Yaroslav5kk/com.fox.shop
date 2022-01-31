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
public class ChangeUserCommandStateApplicationEventHandler {

  private final PaginationDataContext paginationDataContext;
  private final TgChatMessageHistoryService chatMessageHistoryService;
  private final TelegramApiMediator telegramApiMediator;

  public ChangeUserCommandStateApplicationEventHandler(
          final PaginationDataContext paginationDataContext,
          final TgChatMessageHistoryService chatMessageHistoryService,
          final TelegramApiMediator telegramApiMediator
  ) {
    this.paginationDataContext = paginationDataContext;
    this.chatMessageHistoryService = chatMessageHistoryService;
    this.telegramApiMediator = telegramApiMediator;
  }

  @EventListener(ChangeUserCommandStateApplicationEvent.class)
  public void handle(final ChangeUserCommandStateApplicationEvent event) {
    handlePagination(event);
  }

  private void handlePagination(final ChangeUserCommandStateApplicationEvent event) {
    paginationDataContext.setCurrentPage(event.getUserId(), PageResponse.empty());
  }
}
































