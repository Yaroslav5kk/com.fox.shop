package com.fox.shop.client.bot.ui.scenarios;

import com.fox.shop.client.bot.api.client.i.TelegramApiClient;
import com.fox.shop.client.bot.context.i.UserDomainStateContext;
import com.fox.shop.client.bot.context.i.UserModelDataContext;
import com.fox.shop.client.bot.context.i.UserProcessStateContext;
import com.fox.shop.client.bot.service.i.CommandConfigurationService;
import com.fox.shop.client.bot.ui.generate.i.PaginationMessageGenerator;
import com.fox.shop.client.bot.ui.generate.i.PrePostCommandHandleMessageGenerator;
import com.fox.shop.client.bot.ui.generate.keyboard.i.CategoryMessageGenerator;
import com.fox.shop.client.bot.ui.scenarios.i.CategoryScenarios;
import org.springframework.stereotype.Component;

@Component
public class CategoryScenariosImpl implements CategoryScenarios {

    private final CategoryMessageGenerator categoryMessageGenerator;
    private final UserProcessStateContext userProcessStateContext;
    private final UserDomainStateContext userDomainStateContext;
    private final UserModelDataContext userModelDataContext;
    private final TelegramApiClient telegramApiClient;
    private final PrePostCommandHandleMessageGenerator prePostCommandHandleMessageGenerator;
    private final CommandConfigurationService commandConfigurationService;
    private final PaginationMessageGenerator paginationMessageGenerator;

    public CategoryScenariosImpl(
            final CategoryMessageGenerator categoryMessageGenerator,
            final UserProcessStateContext userProcessStateContext,
            final UserDomainStateContext userDomainStateContext,
            final UserModelDataContext userModelDataContext,
            final TelegramApiClient telegramApiClient,
            final PrePostCommandHandleMessageGenerator prePostCommandHandleMessageGenerator,
            final CommandConfigurationService commandConfigurationService,
            final PaginationMessageGenerator paginationMessageGenerator
    ) {
        this.categoryMessageGenerator = categoryMessageGenerator;
        this.userProcessStateContext = userProcessStateContext;
        this.userDomainStateContext = userDomainStateContext;
        this.userModelDataContext = userModelDataContext;
        this.telegramApiClient = telegramApiClient;
        this.prePostCommandHandleMessageGenerator = prePostCommandHandleMessageGenerator;
        this.commandConfigurationService = commandConfigurationService;
        this.paginationMessageGenerator = paginationMessageGenerator;
    }

    @Override
    public PrePostCommandHandleMessageGenerator getPrePostCommandHandleMessageGenerator() {
        return prePostCommandHandleMessageGenerator;
    }

    @Override
    public TelegramApiClient getTelegramApiClient() {
        return telegramApiClient;
    }

    @Override
    public CommandConfigurationService getCommandConfigurationService() {
        return commandConfigurationService;
    }
}

















