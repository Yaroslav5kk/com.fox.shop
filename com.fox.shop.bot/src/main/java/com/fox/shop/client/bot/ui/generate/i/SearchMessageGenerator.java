package com.fox.shop.client.bot.ui.generate.i;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public interface SearchMessageGenerator {
  SendMessage searchTitle(
      long chatId
  );
}
