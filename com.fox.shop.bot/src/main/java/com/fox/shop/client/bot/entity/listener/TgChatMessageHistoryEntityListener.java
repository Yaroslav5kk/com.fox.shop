package com.fox.shop.client.bot.entity.listener;

import com.fox.shop.client.bot.entity.TgChatMessageHistoryEntity;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

@Component
public class TgChatMessageHistoryEntityListener extends AbstractMongoEventListener<TgChatMessageHistoryEntity> {

  @Override
  public void onBeforeConvert(BeforeConvertEvent<TgChatMessageHistoryEntity> event) {
    event.getSource().setId(System.currentTimeMillis());
    super.onBeforeConvert(event);
  }
}
