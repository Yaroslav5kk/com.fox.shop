package com.fox.shop.client.bot.context.i;

import com.fox.shop.client.bot.model.types.CommandData;

public interface UserCommandStateContext {
  void setup(
      int userId,
      CommandData domainState
  );

  CommandData current(Integer userId);
}
