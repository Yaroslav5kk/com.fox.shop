package com.fox.shop.client.bot.ui.scenarios.i;

import com.fox.shop.client.bot.api.client.i.TelegramApiClient;
import com.fox.shop.client.bot.service.i.CommandConfigurationService;
import com.fox.shop.client.bot.ui.generate.i.PrePostCommandHandleMessageGenerator;

public interface Scenarios {

    PrePostCommandHandleMessageGenerator getPrePostCommandHandleMessageGenerator();

    TelegramApiClient getTelegramApiClient();

    CommandConfigurationService getCommandConfigurationService();

    default void preHandle(long chatId, int userId, String command) {
        if (getCommandConfigurationService().existsByCommand(command) && getCommandConfigurationService().get(command).isToSentPreHandleFile())
            getTelegramApiClient().sendPhoto(getPrePostCommandHandleMessageGenerator().preHandle(chatId, userId, command));
    }

    default void postHandle(long chatId, int userId, String command) {
        if (getCommandConfigurationService().existsByCommand(command) && getCommandConfigurationService().get(command).isToSentPostHandleFile())
            getTelegramApiClient().sendPhoto(getPrePostCommandHandleMessageGenerator().postHandle(chatId, userId, command));
    }
}


















