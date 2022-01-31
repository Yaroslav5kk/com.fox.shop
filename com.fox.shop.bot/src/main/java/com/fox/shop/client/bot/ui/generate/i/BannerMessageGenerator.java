package com.fox.shop.client.bot.ui.generate.i;

import com.fox.shop.client.bot.entity.BannerEntity;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public interface BannerMessageGenerator {
  SendMessage build(
      BannerEntity banner,
      long chatId
  );
}
