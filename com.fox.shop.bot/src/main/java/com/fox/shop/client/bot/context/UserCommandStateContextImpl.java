package com.fox.shop.client.bot.context;

import com.fox.shop.client.bot.context.i.UserCommandStateContext;
import com.fox.shop.client.bot.model.types.CommandData;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserCommandStateContextImpl implements UserCommandStateContext {
  private final Map<Long, CommandData> userCommandMap;

  public UserCommandStateContextImpl() {
    userCommandMap = new HashMap<>();
  }

  @Override
  public void setup(
      final long userId,
      final CommandData domainState
  ) {
    userCommandMap.put(userId, domainState);
  }

  @Override
  public CommandData current(final long userId) {
    if (userCommandMap.containsKey(userId))
      return userCommandMap.get(userId);
    userCommandMap.put(userId, CommandData.START);
    return CommandData.START;
  }


}




























