package com.fox.shop.client.bot.events;

import org.springframework.context.ApplicationEvent;

public class TgRemoveMessagesApplicationEvent extends ApplicationEvent {
  private long userId;

  public TgRemoveMessagesApplicationEvent(
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
