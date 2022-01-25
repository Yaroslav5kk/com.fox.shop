package com.fox.shop.client.bot.context.i;

import com.fox.shop.client.bot.model.types.CommandData;

public interface UserCommandStateContext {
  void setup(
      long userId,
      CommandData domainState
  );

  CommandData current(long userId);
}
