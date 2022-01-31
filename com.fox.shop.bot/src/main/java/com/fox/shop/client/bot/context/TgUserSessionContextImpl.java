package com.fox.shop.client.bot.context;

import com.fox.shop.client.bot.context.i.TgUserSessionContext;
import com.fox.shop.client.bot.events.ChangeUserCommandStateApplicationEvent;
import com.fox.shop.client.bot.model.TgUserSessionModel;
import com.fox.shop.client.bot.model.types.CommandData;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class TgUserSessionContextImpl implements TgUserSessionContext {
  private final Map<Long, TgUserSessionModel> userIdSessionModel;
  private final ApplicationEventPublisher applicationEventPublisher;

  public TgUserSessionContextImpl(
          final ApplicationEventPublisher applicationEventPublisher
  ) {
    this.applicationEventPublisher = applicationEventPublisher;
    userIdSessionModel = new HashMap<>();
  }

  @Override
  public void setupCommand(
          final long userId,
          final CommandData command
  ) {
    final TgUserSessionModel session = getSession(userId);
    if (!session.getCommand().equals(command))
      applicationEventPublisher.publishEvent(new ChangeUserCommandStateApplicationEvent(this, userId));
    session.setCommand(command);
  }

  @Override
  public CommandData getCommand(final long userId) {
    return getSession(userId).getCommand();
  }

  @Override
  public TgUserSessionModel getSession(
          final long userId
  ) {
    if (!userIdSessionModel.containsKey(userId)) {
      userIdSessionModel.put(userId, new TgUserSessionModel());
    }
    return userIdSessionModel.get(userId);
  }

  @Override
  public void clearAll(final long userId) {
    userIdSessionModel.remove(userId);
  }
}
