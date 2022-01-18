package com.fox.shop.client.bot.service.processor;

import com.fox.shop.client.bot.annotation.CommandProcessorComponent;
import com.fox.shop.client.bot.api.client.i.TelegramApiClient;
import com.fox.shop.client.bot.context.i.PaginationDataContext;
import com.fox.shop.client.bot.context.i.UserDomainStateContext;
import com.fox.shop.client.bot.context.i.UserModelDataContext;
import com.fox.shop.client.bot.context.i.UserProcessStateContext;
import com.fox.shop.client.bot.model.TgIncomingCommandModel;
import com.fox.shop.client.bot.model.types.CommandData;
import com.fox.shop.client.bot.service.i.CommandConfigurationService;
import com.fox.shop.client.bot.service.i.UserService;
import com.fox.shop.client.bot.service.processor.i.TgCommandProcessorFather;
import com.fox.shop.client.bot.ui.generate.i.GroupsMessageGenerator;
import com.fox.shop.client.bot.ui.generate.i.PrePostCommandHandleMessageGenerator;
import com.fox.shop.client.bot.ui.generate.i.StartMessageGeneratorMenu;

@CommandProcessorComponent
public class GetUsernameTitleProcessorImpl implements TgCommandProcessorFather {
  private final StartMessageGeneratorMenu startMessageGeneratorMenu;
  private final TelegramApiClient telegramApiClient;
  private final UserService userService;
  private final UserProcessStateContext userProcessStateContext;
  private final UserDomainStateContext userDomainStateContext;
  private final UserModelDataContext userModelDataContext;
  private final PrePostCommandHandleMessageGenerator prePostCommandHandleMessageGenerator;
  private final CommandConfigurationService commandConfigurationService;
  private final GroupsMessageGenerator groupsMessageGenerator;

  public GetUsernameTitleProcessorImpl(
          final StartMessageGeneratorMenu startMessageGeneratorMenu,
          final TelegramApiClient telegramApiClient,
          final UserService userService,
          final UserProcessStateContext userProcessStateContext,
          final UserDomainStateContext userDomainStateContext,
          final UserModelDataContext userModelDataContext,
          final PrePostCommandHandleMessageGenerator prePostCommandHandleMessageGenerator,
          final CommandConfigurationService commandConfigurationService,
          final GroupsMessageGenerator groupsMessageGenerator,
          final PaginationDataContext paginationDataContext
  ) {
    this.startMessageGeneratorMenu = startMessageGeneratorMenu;
    this.telegramApiClient = telegramApiClient;
    this.userService = userService;
    this.userProcessStateContext = userProcessStateContext;
    this.userDomainStateContext = userDomainStateContext;
    this.userModelDataContext = userModelDataContext;
    this.prePostCommandHandleMessageGenerator = prePostCommandHandleMessageGenerator;
    this.commandConfigurationService = commandConfigurationService;
    this.groupsMessageGenerator = groupsMessageGenerator;
  }

  @Override
  public void process(final TgIncomingCommandModel incomingCommand) {

  }

  @Override
  public CommandData getResponsibleCommand() {
    return null;
  }
}
