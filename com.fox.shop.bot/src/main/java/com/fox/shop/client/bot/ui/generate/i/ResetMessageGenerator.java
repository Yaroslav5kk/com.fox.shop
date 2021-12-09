package com.fox.shop.client.bot.ui.generate.i;

import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;

public interface ResetMessageGenerator {
  SendPhoto reset(
      long chatId
  );
}
