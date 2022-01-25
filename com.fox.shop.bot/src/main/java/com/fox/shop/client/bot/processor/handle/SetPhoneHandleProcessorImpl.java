package com.fox.shop.client.bot.processor.handle;

import com.fox.shop.client.bot.annotation.CommandProcessorComponent;
import com.fox.shop.client.bot.context.i.UserCommandStateContext;
import com.fox.shop.client.bot.context.i.UserModelDataContext;
import com.fox.shop.client.bot.model.TgIncomingCommandModel;
import com.fox.shop.client.bot.model.types.CommandData;
import com.fox.shop.client.bot.processor.handle.i.TgCommandProcessorFather;
import com.fox.shop.client.bot.service.i.UserService;

@CommandProcessorComponent
public class SetPhoneHandleProcessorImpl implements TgCommandProcessorFather {
  private final UserModelDataContext userModelDataContext;
  private final UserCommandStateContext userCommandStateContext;
  private final UserService userService;

  public SetPhoneHandleProcessorImpl(
      final UserModelDataContext userModelDataContext,
      final UserCommandStateContext userCommandStateContext,
      final UserService userService
  ) {
    this.userModelDataContext = userModelDataContext;
    this.userCommandStateContext = userCommandStateContext;
    this.userService = userService;
  }

  @Override
  public void process(final TgIncomingCommandModel incomingCommand) {
    userModelDataContext.getRegisterUserModel(incomingCommand.getUserId()).setPhone(incomingCommand.getInputData());
    userService.createCustomer(incomingCommand.getUserId(), incomingCommand.getUserName());
    userCommandStateContext.setup(incomingCommand.getUserId(), CommandData.PRODUCTS_BY_GROUP);
  }

  @Override
  public CommandData getResponsibleCommand() {
    return CommandData.GET_PHONE_HANDLE;
  }
}
