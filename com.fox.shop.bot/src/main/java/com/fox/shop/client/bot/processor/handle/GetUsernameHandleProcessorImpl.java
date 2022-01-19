package com.fox.shop.client.bot.processor.handle;

import com.fox.shop.client.bot.annotation.CommandProcessorComponent;
import com.fox.shop.client.bot.context.i.UserCommandStateContext;
import com.fox.shop.client.bot.context.i.UserModelDataContext;
import com.fox.shop.client.bot.model.TgIncomingCommandModel;
import com.fox.shop.client.bot.model.types.CommandData;
import com.fox.shop.client.bot.processor.handle.i.TgCommandProcessorFather;

@CommandProcessorComponent
public class GetUsernameHandleProcessorImpl implements TgCommandProcessorFather {
  private final UserModelDataContext userModelDataContext;
  private final UserCommandStateContext userCommandStateContext;

  public GetUsernameHandleProcessorImpl(
      final UserModelDataContext userModelDataContext,
      final UserCommandStateContext userCommandStateContext
      ) {
    this.userModelDataContext = userModelDataContext;
    this.userCommandStateContext = userCommandStateContext;
  }

  @Override
  public void process(final TgIncomingCommandModel incomingCommand) {
    userModelDataContext.getRegisterUserModel(incomingCommand.getUserId()).setFirstName(incomingCommand.getInputData());
    userCommandStateContext.setup(incomingCommand.getUserId(), CommandData.GET_PHONE);
  }

  @Override
  public CommandData getResponsibleCommand() {
    return CommandData.GET_USERNAME_HANDLE;
  }
}
