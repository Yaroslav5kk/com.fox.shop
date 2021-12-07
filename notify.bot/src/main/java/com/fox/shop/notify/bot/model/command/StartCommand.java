package com.fox.shop.notify.bot.model.command;

public class StartCommand implements TgCommand {

  @Override
  public String getCommandValue() {
    return "start";
  }
}
