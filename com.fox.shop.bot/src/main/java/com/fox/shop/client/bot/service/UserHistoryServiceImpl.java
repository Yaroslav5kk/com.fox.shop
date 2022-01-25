package com.fox.shop.client.bot.service;

import com.fox.shop.client.bot.api.client.i.TelegramApiClient;
import com.fox.shop.client.bot.context.i.UserHistoryContext;
import com.fox.shop.client.bot.model.types.CommandData;
import com.fox.shop.client.bot.service.i.UserHistoryService;
import org.springframework.stereotype.Component;

@Component
public class UserHistoryServiceImpl implements UserHistoryService {

    private final UserHistoryContext userHistoryContext;
    private final TelegramApiClient telegramApiClient;

    public UserHistoryServiceImpl(
            final UserHistoryContext userHistoryContext,
            final TelegramApiClient telegramApiClient
    ) {
        this.userHistoryContext = userHistoryContext;
        this.telegramApiClient = telegramApiClient;
    }

    @Override
    public void snapshot(final int userId, final String command) {
        if (CommandData.BACK.equals(CommandData.fromValue(command)))
            return;
        userHistoryContext.snapshot(userId, command);
    }

    @Override
    public String handle(
            final int userId,
            final String command
    ) {
        switch (UserDomainState.fromCommand(CommandData.fromValue(command))) {
            case BACK:
                return userHistoryContext.back(userId);
            default:
                return command;
        }
    }

    @Override
    public void removeOldMessages(
            final long chatId,
            final UserDomainState domainState
    ) {
        if (!domainState.equals(UserDomainState.VIEW_PRODUCT_DESCRIPTION))
            userHistoryContext.getAndRemoveMessagesByChatId(chatId).
                    forEach(messageId -> telegramApiClient.deleteMessage(chatId, messageId));
    }

}
