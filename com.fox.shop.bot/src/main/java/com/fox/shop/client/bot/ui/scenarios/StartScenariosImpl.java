package com.fox.shop.client.bot.ui.scenarios;

import com.fox.shop.client.bot.api.mediator.TelegramApiMediator;
import com.fox.shop.client.bot.context.i.TgUserSessionContext;
import com.fox.shop.client.bot.model.TgIncomingCommandModel;
import com.fox.shop.client.bot.model.types.CommandData;
import com.fox.shop.client.bot.service.i.UserService;
import com.fox.shop.client.bot.ui.generate.i.GroupsMessageGenerator;
import com.fox.shop.client.bot.ui.generate.i.StartMessageGenerator;
import com.fox.shop.client.bot.ui.scenarios.i.StartScenarios;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StartScenariosImpl implements StartScenarios {

  private final StartMessageGenerator startMessageGenerator;
  private final UserService userService;
  private final TgUserSessionContext tgUserSessionContext;
  private final GroupsMessageGenerator groupsMessageGenerator;
  private final TelegramApiMediator telegramApiMediator;

  public StartScenariosImpl(
      final StartMessageGenerator startMessageGenerator,
      final UserService userService,
      final TgUserSessionContext tgUserSessionContext,
      final GroupsMessageGenerator groupsMessageGenerator,
      final TelegramApiMediator telegramApiMediator
  ) {
    this.startMessageGenerator = startMessageGenerator;
    this.userService = userService;
    this.tgUserSessionContext = tgUserSessionContext;
    this.groupsMessageGenerator = groupsMessageGenerator;
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
    telegramApiMediator.addMessage(startMessageGenerator.start(chatId, Optional.of(tgUserSessionContext.getSession(userId).getCartSessionId())));
    tgUserSessionContext.clearAll(userId);
  }

  @Override
  public void getNameTitle(
      final TgIncomingCommandModel incomingCommand
  ) {
    tgUserSessionContext.setupCommand(incomingCommand.getUserId(), CommandData.GET_USERNAME_HANDLE);
    telegramApiMediator.addMessage(startMessageGenerator.getName(incomingCommand.getChatId()));
  }

  @Override
  public void getNameHandle(
      final TgIncomingCommandModel incomingCommand
  ) {
    tgUserSessionContext.getSession(incomingCommand.getUserId()).getUserModel().setFirstName(incomingCommand.getInputData());
    getPhoneTitle(incomingCommand);
  }

  @Override
  public void getPhoneTitle(
      final TgIncomingCommandModel incomingCommand
  ) {
    tgUserSessionContext.setupCommand(incomingCommand.getUserId(), CommandData.GET_PHONE_HANDLE);
    telegramApiMediator.addMessage(startMessageGenerator.getPhone(incomingCommand.getChatId()));
  }

  @Override
  public void getPhoneHandle(
      final TgIncomingCommandModel incomingCommand
  ) {
    tgUserSessionContext.getSession(incomingCommand.getUserId()).getUserModel().setPhone(incomingCommand.getInputData());
    userService.createCustomer(incomingCommand.getUserId(), incomingCommand.getUserName());
    groupsMessageGenerator.allMainProductGroups(incomingCommand.getChatId(), incomingCommand.getUserId())
        .forEach(sendPhotoFileIdRequest -> telegramApiMediator.addMessage(sendPhotoFileIdRequest));
  }

}
