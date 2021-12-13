package com.fox.shop.client.bot.ui.scenarios;

import com.fox.shop.client.bot.api.client.i.TelegramApiClient;
import com.fox.shop.client.bot.context.i.UserDomainStateContext;
import com.fox.shop.client.bot.context.i.UserModelDataContext;
import com.fox.shop.client.bot.context.i.UserProcessStateContext;
import com.fox.shop.client.bot.model.types.CommandData;
import com.fox.shop.client.bot.service.i.CommandConfigurationService;
import com.fox.shop.client.bot.ui.generate.i.PrePostCommandHandleMessageGenerator;
import com.fox.shop.client.bot.ui.generate.i.ResetMessageGenerator;
import com.fox.shop.client.bot.ui.scenarios.i.ResetScenarios;
import org.springframework.stereotype.Component;

@Component
public class ResetScenariosImpl implements ResetScenarios {

    private final UserModelDataContext userModelDataContext;
    private final UserDomainStateContext userDomainStateContext;
    private final UserProcessStateContext userProcessStateContext;
    private final ResetMessageGenerator resetMessageGenerator;
    private final TelegramApiClient apiClient;
    private final PrePostCommandHandleMessageGenerator prePostCommandHandleMessageGenerator;
    private final CommandConfigurationService commandConfigurationService;

    public ResetScenariosImpl(
            final UserModelDataContext userModelDataContext,
            final UserDomainStateContext userDomainStateContext,
            final UserProcessStateContext userProcessStateContext,
            final ResetMessageGenerator resetMessageGenerator,
            final TelegramApiClient apiClient,
            final PrePostCommandHandleMessageGenerator prePostCommandHandleMessageGenerator,
            final CommandConfigurationService commandConfigurationService
    ) {
        this.userModelDataContext = userModelDataContext;
        this.userDomainStateContext = userDomainStateContext;
        this.userProcessStateContext = userProcessStateContext;
        this.resetMessageGenerator = resetMessageGenerator;
        this.apiClient = apiClient;
        this.prePostCommandHandleMessageGenerator = prePostCommandHandleMessageGenerator;
        this.commandConfigurationService = commandConfigurationService;
    }

    @Override
    public void reset(
            final long chatId,
            final int userId
    ) {
        preHandle(chatId, userId, CommandData.RESET.getValue());
        userModelDataContext.clearAll(userId);
        userDomainStateContext.start(userId);
        userProcessStateContext.free(userId);
        apiClient.sendPhoto(resetMessageGenerator.reset(chatId));
        postHandle(chatId, userId, CommandData.RESET.getValue());
    }

    @Override
    public PrePostCommandHandleMessageGenerator getPrePostCommandHandleMessageGenerator() {
        return prePostCommandHandleMessageGenerator;
    }

    @Override
    public TelegramApiClient getTelegramApiClient() {
        return apiClient;
    }

    @Override
    public CommandConfigurationService getCommandConfigurationService() {
        return commandConfigurationService;
    }
}
