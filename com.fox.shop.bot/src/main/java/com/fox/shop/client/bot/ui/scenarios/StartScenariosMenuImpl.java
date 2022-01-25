package com.fox.shop.client.bot.ui.scenarios;

import com.fox.shop.client.bot.api.client.i.BaseApiClient;
import com.fox.shop.client.bot.api.mediator.TelegramApiMediator;
import com.fox.shop.client.bot.context.i.UserCommandStateContext;
import com.fox.shop.client.bot.context.i.UserModelDataContext;
import com.fox.shop.client.bot.model.TgIncomingCommandModel;
import com.fox.shop.client.bot.model.types.CommandData;
import com.fox.shop.client.bot.service.i.UserService;
import com.fox.shop.client.bot.ui.generate.i.GroupsMessageGenerator;
import com.fox.shop.client.bot.ui.generate.i.StartMessageGeneratorMenu;
import com.fox.shop.client.bot.ui.scenarios.i.StartScenariosMenu;
import org.springframework.stereotype.Service;

@Service
public class StartScenariosMenuImpl implements StartScenariosMenu {

  private final StartMessageGeneratorMenu startMessageGeneratorMenu;
  private final UserService userService;
  private final UserModelDataContext userModelDataContext;
  private final GroupsMessageGenerator groupsMessageGenerator;
  private final UserCommandStateContext userCommandStateContext;
  private final TelegramApiMediator telegramApiMediator;

  public StartScenariosMenuImpl(
      final StartMessageGeneratorMenu startMessageGeneratorMenu,
      final UserService userService,
      final UserModelDataContext userModelDataContext,
      final GroupsMessageGenerator groupsMessageGenerator,
      final UserCommandStateContext userCommandStateContext,
      final TelegramApiMediator telegramApiMediator
      ) {
    this.startMessageGeneratorMenu = startMessageGeneratorMenu;
    this.userService = userService;
    this.userModelDataContext = userModelDataContext;
    this.groupsMessageGenerator = groupsMessageGenerator;
    this.userCommandStateContext = userCommandStateContext;
    this.telegramApiMediator = telegramApiMediator;
  }

  @Override
  public void start(
      final TgIncomingCommandModel incomingCommand
  ) {
    final long userId = incomingCommand.getUserId();
    final long chatId = incomingCommand.getChatId();
    if (!userService.isActivatedUser(userId)) {
      getNameTitle(incomingCommand);
      return;
    } else {
      groupsMessageGenerator.allMainProductGroups(chatId, userId)
          .forEach(sendPhotoFileIdRequest -> telegramApiMediator.addMessage(sendPhotoFileIdRequest));
    }
    telegramApiMediator.addMessage(startMessageGeneratorMenu.base(chatId, userModelDataContext.getCartSessionId(userId)));
    userModelDataContext.clearAll(userId);
  }

  @Override
  public void getNameTitle(
      final TgIncomingCommandModel incomingCommand
  ) {
    userCommandStateContext.setup(incomingCommand.getUserId(), CommandData.GET_USERNAME_HANDLE);
    telegramApiMediator.addMessage(startMessageGeneratorMenu.getName(incomingCommand.getChatId()));
  }

  @Override
  public void getNameHandle(
      final TgIncomingCommandModel incomingCommand
  ) {
    userModelDataContext.getRegisterUserModel(incomingCommand.getUserId()).setFirstName(incomingCommand.getInputData());
    getPhoneTitle(incomingCommand);
  }

  @Override
  public void getPhoneTitle(
      final TgIncomingCommandModel incomingCommand
  ) {
    userCommandStateContext.setup(incomingCommand.getUserId(), CommandData.GET_PHONE_HANDLE);
    telegramApiMediator.addMessage(startMessageGeneratorMenu.getPhone(incomingCommand.getChatId()));
  }

  @Override
  public void getPhoneHandle(
      final TgIncomingCommandModel incomingCommand
  ) {
    userModelDataContext.getRegisterUserModel(incomingCommand.getUserId()).setPhone(incomingCommand.getInputData());
    userService.createCustomer(incomingCommand.getUserId(), incomingCommand.getUserName());
    groupsMessageGenerator.allMainProductGroups(incomingCommand.getChatId(), incomingCommand.getUserId())
        .forEach(sendPhotoFileIdRequest -> telegramApiMediator.addMessage(sendPhotoFileIdRequest));
  }

}
