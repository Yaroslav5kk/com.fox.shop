package com.fox.shop.client.bot.ui.scenarios;

import com.fox.shop.client.bot.api.client.i.TelegramApiClient;
import com.fox.shop.client.bot.context.i.UserDomainStateContext;
import com.fox.shop.client.bot.context.i.UserModelDataContext;
import com.fox.shop.client.bot.context.i.UserProcessStateContext;
import com.fox.shop.client.bot.model.types.CommandData;
import com.fox.shop.client.bot.service.i.CommandConfigurationService;
import com.fox.shop.client.bot.service.i.UserService;
import com.fox.shop.client.bot.ui.generate.i.GroupsMessageGenerator;
import com.fox.shop.client.bot.ui.generate.i.PrePostCommandHandleMessageGenerator;
import com.fox.shop.client.bot.ui.generate.i.StartMessageGeneratorMenu;
import com.fox.shop.client.bot.ui.scenarios.i.StartScenariosMenu;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.User;

@Service
public class StartScenariosMenuImpl implements StartScenariosMenu {

  private final StartMessageGeneratorMenu startMessageGeneratorMenu;
  private final TelegramApiClient telegramApiClient;
  private final UserService userService;
  private final UserProcessStateContext userProcessStateContext;
  private final UserDomainStateContext userDomainStateContext;
  private final UserModelDataContext userModelDataContext;
  private final PrePostCommandHandleMessageGenerator prePostCommandHandleMessageGenerator;
  private final CommandConfigurationService commandConfigurationService;
  private final GroupsMessageGenerator groupsMessageGenerator;

  public StartScenariosMenuImpl(
      final StartMessageGeneratorMenu startMessageGeneratorMenu,
      final TelegramApiClient telegramApiClient,
      final UserService userService,
      final UserProcessStateContext userProcessStateContext,
      final UserDomainStateContext userDomainStateContext,
      final UserModelDataContext userModelDataContext,
      final PrePostCommandHandleMessageGenerator prePostCommandHandleMessageGenerator,
      final CommandConfigurationService commandConfigurationService,
      final GroupsMessageGenerator groupsMessageGenerator
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
  public void base(
      final Long chatId,
      final User user
  ) {
    final int userId = user.getId();
    preHandle(chatId, userId, CommandData.START.getValue());
    if (!userService.isActivatedUser(userId)) {
      getNameTitle(chatId, userId);
      return;
    } else {
      groupsMessageGenerator.allMainProductGroups(chatId, user.getId())
          .forEach(sendPhotoFileIdRequest -> telegramApiClient.sendPhoto(sendPhotoFileIdRequest));
    }
    telegramApiClient.sendMessage(startMessageGeneratorMenu.base(chatId, userModelDataContext.getCartSessionId(userId)));
    userModelDataContext.clearAll(userId);
    postHandle(chatId, userId, CommandData.START.getValue());
  }

  @Override
  public void getNameTitle(
      final long chatId,
      final int userId
  ) {
    userDomainStateContext.setName(userId);
    telegramApiClient.sendMessage(startMessageGeneratorMenu.getName(chatId));
  }

  @Override
  public void getNameHandle(
      final long chatId,
      final String name,
      final int userId
  ) {
    userModelDataContext.getRegisterUserModel(userId).setFirstName(name);
    getPhoneTitle(chatId, userId);
  }

  @Override
  public void getPhoneTitle(
      final long chatId,
      final int userId
  ) {
    userDomainStateContext.setPhone(userId);
    telegramApiClient.sendMessage(startMessageGeneratorMenu.getPhone(chatId));
  }

  @Override
  public void getPhoneHandle(
      final long chatId,
      final User user,
      final String phone
  ) {
   userModelDataContext.getRegisterUserModel(user.getId()).setPhone(phone);
    userService.createCustomer(user);
    groupsMessageGenerator.allMainProductGroups(chatId, user.getId())
        .forEach(sendPhotoFileIdRequest -> telegramApiClient.sendPhoto(sendPhotoFileIdRequest));
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
