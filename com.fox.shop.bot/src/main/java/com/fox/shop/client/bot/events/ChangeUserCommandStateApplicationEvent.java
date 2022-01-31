package com.fox.shop.client.bot.events;

import com.fox.shop.client.bot.model.types.CommandData;
import org.springframework.context.ApplicationEvent;

public class ChangeUserCommandStateApplicationEvent extends ApplicationEvent {
  private long userId;

  public ChangeUserCommandStateApplicationEvent(
          Object source,
          long userId
  ) {
    super(source);
    this.userId = userId;
  }

  public long getUserId() {
    return userId;
  }

  public void setUserId(long userId) {
    this.userId = userId;
  }

}
